package ec.edu.ups.models;
import ec.edu.ups.models.*;
import ec.edu.ups.models.Enums.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_USER")
public class UserModel {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UserModel id;

    @Column(unique = true, nullable = false)
    private String firebaseUid;

    private String email;
    private String displayName;
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private boolean deleted = false;

    @OneToOne(mappedBy = "user")	
    private ProgrammerProfileModel profile;

    //@OneToMany(mappedBy = "user")
    //private List<SocialLink> socialLinks;

    @OneToMany(mappedBy = "requester")
    private List<BookingModel> bookingsSent;
}
