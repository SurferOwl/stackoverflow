package com.example.project_ps_real.controller;

import com.example.project_ps_real.entity.Vote;
import com.example.project_ps_real.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Vote> getAllVotes(){
        return this.voteService.retrieveAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    public Vote getVotesById(@RequestParam("id") Long id) {
        System.out.println("Received request for post ID: " + id);
        return this.voteService.findByIdVotes(id);
    }

    @PostMapping("/addVotes")
    @ResponseBody
    public Vote addVotes(@RequestBody Vote vote) {
        return this.voteService.addVotes(vote);
    }

    @PutMapping("/updateVotes")
    @ResponseBody
    public Vote updateVotes(@RequestBody Vote vote) {
        return this.voteService.updateVotes(vote);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteVotesById(@RequestParam("id") Long id) {
        System.out.println("Received request for post ID: " + id);
        return this.voteService.deleteVotesById(id);

    }
}
