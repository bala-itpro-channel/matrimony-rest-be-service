package com.javatalent.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Photo { 
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "photo_id")
    private int photoId;
	
	@Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

	@Column(name = "is_primary")
	private boolean isPrimary;
	
	public Photo() {
	}
	
	public Photo(byte[] image, boolean isPrimary) { 
		super(); 
		this.image = image;
		this.isPrimary = isPrimary; 
	}
	
	 /* 
	 * public Photos(int photoId, byte[] image, boolean isPrimary) { super();
	 * this.photoId = photoId; this.image = image; this.isPrimary = isPrimary; }
	 */

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

} 