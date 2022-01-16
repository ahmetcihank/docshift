package org.docshift.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser extends TimestampEntity {
    @Id
    @SequenceGenerator(
            name = "appuser_id_sequece",
            sequenceName = "appuser_id_sequece"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appuser_id_sequece"
    )
    private Long id;
    private String name;
    private String lastname;

    @Column(unique=true)
    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "appUser")
    private AppUserProfile userProfile;

    @Override
    public String toString() {
            StringBuilder builder = new StringBuilder();
        builder.append("User{id=").append(id).append(", name=")
                .append(name).append(", email=");

        return builder.toString();
    }

}
