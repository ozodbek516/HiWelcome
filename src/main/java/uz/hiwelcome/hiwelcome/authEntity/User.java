    package uz.hiwelcome.hiwelcome.authEntity;

    import jakarta.persistence.*;
    import lombok.*;
    import uz.hiwelcome.hiwelcome.base.TimeLong;
    import uz.hiwelcome.hiwelcome.enums.Role;


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "users")
    public class User extends TimeLong {

        private String name;

        private String password;

        private String email;

        @Column(nullable = false)
        private Boolean active = false;

        @Column(unique = true, nullable = false, length = 13)
        private String phoneNumber;

        @Column(nullable = false)
        private boolean phoneVerified = false;

        @Column(length = 2048)
        private String fcmToken;

//        @Column(nullable = false)
//        private String imageUrl;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;


    }

