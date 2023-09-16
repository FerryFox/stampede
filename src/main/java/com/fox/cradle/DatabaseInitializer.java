package com.fox.cradle;

import com.fox.cradle.configuration.security.user.User;
import com.fox.cradle.configuration.security.user.UserRepository;
import com.fox.cradle.features.appuser.model.AppUser;
import com.fox.cradle.features.appuser.service.AppUserService;
import com.fox.cradle.features.picture.model.Picture;
import com.fox.cradle.features.picture.service.PictureService;
import com.fox.cradle.features.stamp.model.*;
import com.fox.cradle.features.stamp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner
{
    private final AppUserService appUserService;
    private final UserRepository userRepository;
    private final StampCardTemplateService stampCardTemplateService;
    private final StampCardService stampCardService;
    private final StampService stampService;
    private final PictureService pictureService;

    @Override
    public void run(String... args) throws Exception
    {
        //fill mongo db with some pictures
/*
        var ice = pictureService.loadPictureFromFile("ice");
        pictureService.savePicture(ice);

        var coffee = pictureService.loadPictureFromFile("coffee");
        pictureService.savePicture(coffee);

        var cinema = pictureService.loadPictureFromFile("cinema");
        pictureService.savePicture(cinema);
*/

//create some users
    //User 1 with AppUser 1
        User user1 = new User();
        user1.setEmail("w@w");
        user1.setPassword("1234");
        user1.setReceiveNews(true);
        user1.setFirstname("Ice cream man");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        userRepository.save(user1);

        AppUser appUser1 = new AppUser();
        appUser1.setAppUserName("Ice cream man");
        appUser1.setAppUserEmail("w@w");
        appUser1.setReceiveNews(true);
        appUserService.saveAppUser(appUser1);

    //User 2 with AppUser 2
        User user2 = new User();
        user2.setEmail("q@q");
        user2.setPassword("123");
        user2.setReceiveNews(true);
        user2.setFirstname("Bob");
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        userRepository.save(user2);

        AppUser appUser2 = new AppUser();
        appUser2.setAppUserName("Bob");
        appUser2.setAppUserEmail("q@q");
        appUser2.setReceiveNews(true);
        appUserService.saveAppUser(appUser2);

//Create some stamp card templates
        StampCardTemplate stampCardTemplate_001 = new StampCardTemplate();
        stampCardTemplate_001.setName("Ice Cream Card");
        stampCardTemplate_001.setImage("6505fc2aa54d6f41e4374591");
        stampCardTemplate_001.setStampCardCategory(StampCardCategory.FOOD);
        stampCardTemplate_001.setStampCardSecurity(StampCardSecurity.TRUSTUSER);
        stampCardTemplate_001.setStampCardStatus(StampCardStatus.PUBLIC);
        stampCardTemplate_001.setDescription("Buy 10 ice creams and get one for free");
        stampCardTemplate_001.setCreatedBy(appUser1.getAppUserName());
        stampCardTemplate_001.setAppUser(appUser1);
        stampCardTemplateService.save(stampCardTemplate_001);

        StampCardTemplate stampCardTemplate_002 = new StampCardTemplate();
        stampCardTemplate_002.setName("Coffee Card");
        stampCardTemplate_002.setImage("6505fc2aa54d6f41e4374592");
        stampCardTemplate_002.setStampCardCategory(StampCardCategory.DRINK);
        stampCardTemplate_002.setStampCardSecurity(StampCardSecurity.TRUSTUSER);
        stampCardTemplate_002.setStampCardStatus(StampCardStatus.PUBLIC);
        stampCardTemplate_002.setDescription("Buy 10 coffees and get one for free");
        stampCardTemplate_002.setCreatedBy(appUser2.getAppUserName());
        stampCardTemplate_002.setAppUser(appUser2);
        stampCardTemplateService.save(stampCardTemplate_002);

        StampCardTemplate stampCardTemplate_003 = new StampCardTemplate();
        stampCardTemplate_003.setName("Cinema Card");
        stampCardTemplate_003.setImage("6505fc2aa54d6f41e4374593");
        stampCardTemplate_003.setStampCardCategory(StampCardCategory.ENTERTAINMENT);
        stampCardTemplate_003.setStampCardSecurity(StampCardSecurity.TRUSTUSER);
        stampCardTemplate_003.setStampCardStatus(StampCardStatus.PUBLIC);
        stampCardTemplate_003.setDescription("Visit the cinema 5 times to get a free ticket");
        stampCardTemplate_003.setCreatedBy(appUser2.getAppUserName());
        stampCardTemplate_003.setAppUser(appUser2);
        stampCardTemplateService.save(stampCardTemplate_003);


//Create a stamp card and from a template and add it to AppUser 1
        AppUser bob = appUser2;
        stampCardService.createStampCard(stampCardTemplate_001, bob);
        //stampCardService.createStampCard(stampCardTemplate_002, appUser1);

//test stamp card loading with template


        var stampcard = stampCardService.getStampCardById(1);
        System.out.println("-----Stampcard printing-----");
        System.out.println(stampcard);

        System.out.println("----Bob Print------");
        System.out.println("Bob (AppUser) stamp cards");
        bob.getMyStampCards().forEach(StampCard::smallPrint);

//load bob new and check
        AppUser unknown = appUserService.getAppUserById(bob.getId());
        System.out.println("----Bob2 Print------");
        System.out.println("Bob2 (AppUser) s    tamp cards");
        unknown.getMyStampCards().forEach(StampCard::smallPrint);

//try stamp some card
        StampCard stampCard = unknown.getMyStampCards().get(0);
        stampService.stampACard(stampCard);
        System.out.println("----Bob3 Print------");
        System.out.println("Bo3 (AppUser) stamp cards");
        unknown.getMyStampCards().forEach(x -> System.out.println(x));



        }
}
