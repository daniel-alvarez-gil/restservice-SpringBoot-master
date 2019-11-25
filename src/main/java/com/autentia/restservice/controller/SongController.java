package com.autentia.restservice.controller;

import com.autentia.restservice.dto.ErrorDTO;
import com.autentia.restservice.dto.SongPrinceOnly;
import com.autentia.restservice.dto.SongRate;
import com.autentia.restservice.model.Song;
import com.autentia.restservice.service.SongService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/songs")
@Api(value = "Song Resource", description = "This API has a CRUD for Song")
public class SongController {

    private static String FILTER_NAME = "fieldFilter";

    @Autowired
    SongService songService;

    @GetMapping()
    @ApiOperation(value = "Find all songs", notes = "Return all songs" )
    private List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a song", notes = "Return a song by Id",response = Song.class)
    public Song getSong(@PathVariable("id") long id) {

        return songService.getSongById(id);
    }

    @GetMapping("/top3/recently/rock")
    @ApiOperation(value = "Find top 3 rock songs", notes = "Return top 3 rock songs by release" )
    public List<Song> useAlias() {

    return this.search("genre:rock").stream().limit(3)
                .sorted(Comparator.comparing(Song::getRelease))
                .collect(Collectors.toList());

    }

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "Search songs", notes = "Return all songs by filter" )
    public List<Song> search(@RequestParam(value = "search") String search) {
        SongSpecificationsBuilder builder = new SongSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Song> spec = builder.build();
        return songService.getAllSongs(spec);
    }

    @GetMapping("/searchByGenreOrPrice")
    @ApiOperation(value = "Search songs by genre or price", notes = "Return all songs by filter" )
    public List<?> search(@RequestParam(value = "genre", required = false) String genre,
                          @RequestParam(value = "price", required = false) Double price) {
        if( genre == null && price == null) {
            List<Object> error = new ArrayList<>();
            error.add(new ErrorDTO(true, "Tiene que tener al menos un parametro la petición"));
            return error;
        }

        return songService.search(genre, price);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a song")
    public void deleteSong(@PathVariable("id") int id) {
        songService.delete(id);
    }

    @PostMapping()
    @ApiOperation(value = "Save a song", notes = "Return a song Id" )
    public long saveSong(@RequestBody Song song) {
        songService.saveOrUpdate(song);
        return song.getSongId();
    }


    @ApiOperation(value = "Save or Update a song", notes = "Return a song Id" )
    @PutMapping()
    public long updateSong(@RequestBody Song song) {
        songService.saveOrUpdate(song);
        return song.getSongId();
    }

    @PatchMapping()
    @ApiOperation(value = "Update a song´s price", notes = "Return a song Id" )
    public ResponseEntity updatePartialSong(@RequestBody SongPrinceOnly song) {
        Song manager = songService.getSongById(song.getId());
        manager.setPrice(song.getPrice());
        songService.saveOrUpdate(manager);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @GetMapping("rate/{title}")
    @ApiOperation(value = "Get song rate from external api", notes = "Return a song rate" )
    public SongRate getRateSong(@PathVariable("title") String title){
        final String uri = "http://localhost:8081/apiExternal/v1/songs/"+title;

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, SongRate.class);
    }

    @GetMapping(path = "/page")
    Page<Song> loadSongsPage(Pageable pageable) {
        return songService.findAllPage(pageable);
    }

    @GetMapping("/filter/{id}")
    @ApiOperation(value = "Find a song", notes = "Return a song by Id",response = Song.class)
    public String getSongFiltered(@PathVariable("id") long id,@RequestParam(value="requiredFields",required=false ) String requiredFields) {

        Song song = songService.getSongById(id);

        ObjectMapper mapper = new ObjectMapper();

        String json="";
        try {
            if (requiredFields!= null) {
                String[] fields = requiredFields.split("\\,");

                Set<String> properties = new HashSet<>(Arrays.asList(fields));

                SimpleBeanPropertyFilter filter =
                        new SimpleBeanPropertyFilter.FilterExceptFilter(properties);
                SimpleFilterProvider fProvider = new SimpleFilterProvider();
                fProvider.addFilter(FILTER_NAME, filter);

                mapper.setAnnotationIntrospector( new AnnotationIntrospector() );

                json = mapper.writer(fProvider).writeValueAsString(song);

            } else {
                SimpleFilterProvider fp = new SimpleFilterProvider().setFailOnUnknownId(false);
                mapper.setFilterProvider(fp);
                json =mapper.writeValueAsString(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    private static class AnnotationIntrospector extends JacksonAnnotationIntrospector {
        @Override
        public Object findFilterId(Annotated a) {
            return FILTER_NAME;
        }
    }

    @GetMapping(path = "/secure")
    String secure() {
        return "Premium Zone";
    }
}
