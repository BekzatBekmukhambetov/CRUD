package kz.beka.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name ="images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "OriginalFilename")
    private String OriginalFilename;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "size")
    private Long size;


    @Lob
    private byte[]bytes;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;
}
