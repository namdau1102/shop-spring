package com.project.shop.config;

import com.project.shop.models.*;
import com.project.shop.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class RepositoryInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PetRepository petRepository;
    private final AccessoryRepository accessoryRepository;
    private final FeedRepository feedRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RepositoryInitializer(RoleRepository roleRepository, UserRepository userRepository, AddressRepository addressRepository, PetRepository petRepository, AccessoryRepository accessoryRepository, FeedRepository feedRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.petRepository = petRepository;
        this.accessoryRepository = accessoryRepository;
        this.feedRepository = feedRepository;
    }

    @Bean
    public InitializingBean initRepositories() {
        return () -> {
            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(new Role(Role.UserTypes.ROLE_ADMIN));
                roleRepository.save(new Role(Role.UserTypes.ROLE_USER));
            }

            if (orderTypeRepository.findAll().isEmpty()) {
                OrderType orderType = new OrderType();
              
                orderType.setOrderStatus(OrderType.OrderStatus.ORDER_ORDERED);

                OrderType orderType2 = new OrderType();
                orderType2.setOrderStatus(OrderType.OrderStatus.ORDER_COMPLETED);

                OrderType orderType3 = new OrderType();
                orderType3.setOrderStatus(OrderType.OrderStatus.ORDER_CANCELED);
                orderTypeRepository.save(orderType);
                orderTypeRepository.save(orderType2);
                orderTypeRepository.save(orderType3);
            }

            if (userRepository.findAll().isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setPasswordConfirm(admin.getPassword());
                admin.setAddress(new Address());
                admin.setEmail("admin@admin.pl");
                admin.setEnabled(true);
                admin.setRoles(new HashSet<>(Arrays.asList(roleRepository.findRoleByUserType(Role.UserTypes.ROLE_ADMIN), roleRepository.findRoleByUserType(Role.UserTypes.ROLE_USER))));
                userRepository.save(admin);


                User user = new User();
                user.setUsername("user");
                user.setEnabled(true);
                user.setPassword(passwordEncoder.encode("user"));
                user.setPasswordConfirm(user.getPassword());
                user.setAddress(new Address());
                user.setEmail("user@user.pl");
                user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findRoleByUserType(Role.UserTypes.ROLE_USER))));
                userRepository.save(user);

                User user2 = new User();
                user2.setUsername("user2");
                user2.setEnabled(true);
                user2.setPassword(passwordEncoder.encode("user2"));
                user2.setPasswordConfirm(user2.getPassword());
                user2.setAddress(new Address());
                user2.setEmail("user2@user2.pl");
                user2.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findRoleByUserType(Role.UserTypes.ROLE_USER))));
                userRepository.save(user2);
            }

            if (typeRepository.findAll().isEmpty()) {
                typeRepository.save(new Type("Kot"));
                typeRepository.save(new Type("Pies"));
                typeRepository.save(new Type("Wąż"));
                typeRepository.save(new Type("Ptak"));
                typeRepository.save(new Type("Pająk"));
            }

            Type catType = typeRepository.findById(1L).orElse(null);
            Type dogType = typeRepository.findById(2L).orElse(null);
            Type snakeType = typeRepository.findById(3L).orElse(null);
            Type birdType = typeRepository.findById(4L).orElse(null);
            Type spiderType = typeRepository.findById(5L).orElse(null);

            if (feedRepository.findAll().isEmpty()) {

                Feed feed = new Feed();
                feed.setName("Royal Canin Lite");
                feed.setDescription("Thức ăn cho mèo nhỏ. Thành phần thịt lợn 60%, thịt bò 40%");
                feed.setDeleted(false);
                feed.setGram(1000f);
                feed.setPrice(60f);
                feed.setAmount(200);
                feed.setType(catType);
                feed.setImages(Arrays.asList("https://i.imgur.com/MlttkcR.jpg",
                        "https://i.imgur.com/FVayjze.jpg",
                        "https://i.imgur.com/9nINKIo.jpg"));
                feedRepository.save(feed);

                Feed feed2 = new Feed();
                feed2.setName("Wild Bird Feed");
                feed2.setDescription("Karma dla ptaków. Bardzo smaczna.");
                feed2.setDeleted(false);
                feed2.setGram(2000f);
                feed2.setAmount(300);
                feed2.setPrice(50f);
                feed2.setType(birdType);
                feed2.setImages(Arrays.asList("https://i.imgur.com/tlbpIB9.jpg",
                        "https://i.imgur.com/8YntYZ3.jpg",
                        "https://i.imgur.com/M1aM0bM.jpg"));
                feedRepository.save(feed2);

                Feed feed3 = new Feed();
                feed3.setName("Liście koniczyny");
                feed3.setDescription("Liście koniczyny w paczce. Suszone, idealne dla pająków");
                feed3.setDeleted(false);
                feed3.setGram(2000f);
                feed3.setPrice(20f);
                feed3.setAmount(150);
                feed3.setType(spiderType);
                feed3.setImages(Arrays.asList("https://i.imgur.com/MlttkcR.jpg",
                        "https://i.imgur.com/FVayjze.jpg",
                        "https://i.imgur.com/9nINKIo.jpg"));
                feedRepository.save(feed3);
            }

            if (accessoryRepository.findAll().isEmpty()) {
                Accessory accessory = new Accessory();
                accessory.setAmount(100);
                accessory.setBrand("BestBrand");
                accessory.setDeleted(false);
                accessory.setDescription("Niebieska smycz dla psa. Długość 2m");
                accessory.setName("Smycz dla psa");
                accessory.setPrice(70f);
                accessory.setType(dogType);
                accessory.setImages(Arrays.asList("https://i.imgur.com/qAv2u1o.jpg",
                        "https://i.imgur.com/rgq1w7w.jpg",
                        "https://i.imgur.com/fuFFy04.jpg"));
                accessoryRepository.save(accessory);

                Accessory accessory2 = new Accessory();
                accessory2.setAmount(50);
                accessory2.setBrand("Trixie");
                accessory2.setDeleted(false);
                accessory2.setDescription("Legowisko dla kota. Wymiary 56x49x12.");
                accessory2.setName("Legowisko Trixie");
                accessory2.setPrice(50f);
                accessory2.setType(catType);
                accessory2.setImages(Arrays.asList("https://i.imgur.com/e5DtPSw.jpg",
                        "https://i.imgur.com/RiyWvk6.jpg",
                        "https://i.imgur.com/hpOudtR.jpg"));
                accessoryRepository.save(accessory2);

                Accessory accessory3 = new Accessory();
                accessory3.setAmount(100);
                accessory3.setBrand("GlassCompany");
                accessory3.setDeleted(false);
                accessory3.setDescription("Szklane, małe terrarium. Idealne dla węża");
                accessory3.setName("Szklane terrarium");
                accessory3.setPrice(1500f);
                accessory3.setType(snakeType);
                accessory3.setImages(Arrays.asList("https://i.imgur.com/gscxDEA.jpg",
                        "https://i.imgur.com/LANOPea.jpg",
                        "https://i.imgur.com/6wEUc4s.jpg"));
                accessoryRepository.save(accessory3);
            }

            if (petRepository.findAll().isEmpty()) {
                Pet pet = new Pet();
                pet.setSold(false);
                pet.setType(birdType);
                pet.setAge(12f);
                pet.setName("Ara czerwona");
                pet.setDescription("Niebieska papuga o bardzo ładnym kolorze");
                pet.setPrice(2000f);
                pet.setAmount(1);
                pet.setImages(Arrays.asList("https://i.imgur.com/pUOKtgT.jpg",
                        "https://i.imgur.com/rxFIGNp.jpg",
                        "https://i.imgur.com/LAVdwF2.jpg"));
                pet.setDeleted(false);
                petRepository.save(pet);

                Pet pet2 = new Pet();
                pet2.setSold(false);
                pet2.setType(snakeType);
                pet2.setAge(6f);
                pet2.setName("Mamba pospolita");
                pet2.setDescription("Zielony wąż, długość około 1m. Bardzo niebezpieczny");
                pet2.setPrice(3000f);
                pet2.setAmount(1);
                pet2.setImages(Arrays.asList("https://i.imgur.com/BwhAbvI.jpg",
                        "https://i.imgur.com/5KWBTiX.jpg",
                        "https://i.imgur.com/J5QoGO3.jpg"));
                pet2.setDeleted(false);
                petRepository.save(pet2);

                Pet pet3 = new Pet();
                pet3.setSold(false);
                pet3.setType(spiderType);
                pet3.setAge(10f);
                pet3.setName("Phidippus Regius");
                pet3.setDescription("Ptasznik około 6-8cm wielkości w barwie pomarańczowo szarej");
                pet3.setPrice(1000f);
                pet3.setAmount(1);
                pet3.setImages(Arrays.asList("https://i.imgur.com/1jomxFk.jpg",
                        "https://i.imgur.com/erB7wt2.jpg",
                        "https://i.imgur.com/dLZ9vtS.jpg"));
                pet3.setDeleted(false);
                petRepository.save(pet);
            }
        };
    }
}
