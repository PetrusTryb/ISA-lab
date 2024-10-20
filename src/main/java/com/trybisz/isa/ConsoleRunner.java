package com.trybisz.isa;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import com.trybisz.isa.offer.service.api.OfferService;
import com.trybisz.isa.partner.service.api.PartnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final OfferService offerService;
    private final PartnerService partnerService;

    public ConsoleRunner(OfferService offerService, PartnerService partnerService) {
        this.offerService = offerService;
        this.partnerService = partnerService;
    }

    @Override
    public void run(String... args){
        boolean shouldStop = false;
        Scanner scanner = new Scanner(System.in);
        while(!shouldStop){
            System.out.print("> ");
            String command = scanner.nextLine();
            switch (command){
                case "exit" -> shouldStop = true;
                case "help" -> System.out.println("""
                        help - prints this information
                        list [partners | offers] - prints all partners or offers stored in database
                        add [partner | offer] - creates new partner or offer and stores it in database
                        del [partner | offer] - removes selected partner or offer from database
                        exit - closes the app
                        """);
                case "list partners" -> this.partnerService.findAll().forEach(System.out::println);
                case "list offers" -> this.offerService.findAll().forEach(System.out::println);
                case "add partner" ->{
                    System.out.println("Name: ");
                    String partnerName = scanner.nextLine();
                    System.out.println("Website: ");
                    URL partnerWebsite;
                    try {
                        partnerWebsite = new URL(scanner.nextLine());
                    } catch (MalformedURLException e) {
                        System.err.println("Invalid URL");
                        continue;
                    }
                    System.out.println("Partner since year: ");
                    int partnerSince = scanner.nextInt();
                    Partner p = Partner.builder()
                            .Name(partnerName)
                            .Website(partnerWebsite)
                            .SinceYear(partnerSince)
                            .build();
                    this.partnerService.save(p);
                    System.out.println("Created new Partner with ID: "+p.getId());
                    scanner.nextLine();
                }
                case "add offer" -> {
                    System.out.println("Title: ");
                    String offerTitle = scanner.nextLine();
                    System.out.println("Description: ");
                    String offerDesc = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date validFrom, validTo;
                    try{
                        System.out.println("Valid from (dd.MM.yyyy): ");
                        validFrom = dateFormat.parse(scanner.nextLine());
                        System.out.println("Valid to (dd.MM.yyyy): ");
                        validTo = dateFormat.parse(scanner.nextLine());
                        if(validTo.before(validFrom)){
                            System.err.println("Valid From date must be earlier that Valid To date");
                            continue;
                        }
                    } catch (ParseException e) {
                        System.err.println("Invalid date format");
                        continue;
                    }
                    List<Partner> partnerList = this.partnerService.findAll();
                    System.out.println("Select offering partner: ");
                    IntStream.range(0, partnerList.size())
                            .forEach(idx ->
                                    System.out.println(idx+". "+partnerList.get(idx))
                            );
                    int partnerIndex = scanner.nextInt();
                    Partner offeringPartner = partnerList.get(partnerIndex);
                    Offer o = Offer.builder()
                            .Title(offerTitle)
                            .Description(offerDesc)
                            .ValidFrom(validFrom)
                            .ValidTo(validTo)
                            .partner(offeringPartner)
                            .build();
                    this.offerService.save(o);
                    System.out.println("Created new Offer with ID: "+o.getId());
                    scanner.nextLine();
                }
                case "del partner" -> {
                    System.out.println("Partner UUID: ");
                    UUID toDelete;
                    try {
                        toDelete = UUID.fromString(scanner.nextLine());
                        this.partnerService.delete(toDelete);
                    }catch (IllegalArgumentException e){
                        System.err.println("Invalid or non-existent UUID");
                        continue;
                    }
                    System.out.println("Deleted Partner with ID: "+toDelete);
                }
                case "del offer" -> {
                    System.out.println("Offer UUID: ");
                    UUID toDelete;
                    try {
                        toDelete = UUID.fromString(scanner.nextLine());
                        this.offerService.delete(toDelete);
                    }catch (IllegalArgumentException e){
                        System.err.println("Invalid or non-existent UUID");
                        continue;
                    }
                    System.out.println("Deleted Offer with ID: "+toDelete);
                }
                default -> System.err.println("Unknown command! Type \"help\" for list of commands.\n");
            }
        }
    }
}
