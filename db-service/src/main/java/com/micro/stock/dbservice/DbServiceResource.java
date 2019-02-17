package com.micro.stock.dbservice;

import com.micro.stock.dbservice.model.Quotes;
import com.micro.stock.dbservice.model.dto.QuotesDto;
import com.micro.stock.dbservice.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuoteRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {

        return getQuoteByusername(username);

    }

    private List<String> getQuoteByusername(@PathVariable("username") String username) {
        return quotesRepository.findByUsername(username)
                .stream()
                .map(Quotes::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> addQuote(@RequestBody final QuotesDto quotesDto) {

        quotesDto.getQuotes()
                .stream()
                .map(quote -> new Quotes(quotesDto.getUsername(), quote))
                .forEach(quote -> quotesRepository.save(quote));

        return getQuoteByusername(quotesDto.getUsername());
    }
}
