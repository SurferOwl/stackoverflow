package com.example.project_ps_real.service;

import com.example.project_ps_real.entity.Vote;
import com.example.project_ps_real.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> retrieveAll() {
        return (List<Vote>) this.voteRepository.findAll();
    }

    public Vote findByIdVotes(Long id) {
        Optional<Vote> votes = this.voteRepository.findById(id);
        if(votes.isPresent()){
            return votes.get();
        }else{
            return null;
        }
    }

    public Vote addVotes(Vote vote) {
        System.out.println("Votes: " + vote.toString());
        return this.voteRepository.save(vote);
    }


    public Vote updateVotes(Vote newVote) {
        Optional<Vote> optionalVote = voteRepository.findById(newVote.getVoteId());

        if (optionalVote.isPresent()) {
            Vote existingVote = optionalVote.get();
            existingVote.setLiked(newVote.isLiked());

            System.out.println(newVote.isLiked());
            System.out.println(existingVote.isLiked());

            return voteRepository.save(existingVote);
        } else {
            throw new RuntimeException("Vote not found with ID: " + newVote.getVoteId());
        }
    }



    public String deleteVotesById(Long id) {
        Optional<Vote> votes = this.voteRepository.findById(id);
        if(votes.isPresent()) {
            this.voteRepository.deleteById(id);
            return "Entry successfully deleted!";
        }
        else {
            return "Failed to delete user with id " + id;
        }
    }


}
