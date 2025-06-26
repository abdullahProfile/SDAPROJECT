package controller;

import model.Proposal;
import storage.FileStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProposalController {

    public List<Proposal> getAllProposals() {
        List<Proposal> proposals = new ArrayList<>();
        List<String> lines = FileStorage.readLines("proposal.txt");

        for (String line : lines) {
            try {
                Proposal proposal = Proposal.fromFileString(line);
                proposals.add(proposal);
            } catch (Exception e) {
                System.err.println("Error parsing proposal: " + line);
            }
        }

        return proposals;
    }

    public List<String> getRawProposalLines() {
        return FileStorage.readLines("proposal.txt");
    }
}