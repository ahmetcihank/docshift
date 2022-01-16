package org.docshift.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUserProfile extends TimestampEntity {
    @Id
    @SequenceGenerator(
            name = "profile_id_sequece",
            sequenceName = "profile_id_sequece"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_id_sequece"
    )
    private Long id;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser appUser;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(", phoneNumber=")
                .append(phoneNumber).append(", gender=").append(gender)
                .append(", dateOfBirth=").append(dateOfBirth)
                .append(", address=").append(address).append("}");

        return builder.toString();
    }
}
