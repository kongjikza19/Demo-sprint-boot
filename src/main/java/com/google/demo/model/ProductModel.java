package com.google.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

//@Data
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class ProductModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

	@Column(length = 150, nullable = false, unique = false)
	private String name;
	private String image;
	private int price;
	private int stock;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date",nullable = false)
	private Date createDate;

	@Setter(AccessLevel.NONE)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date",nullable = false)
	private Date updateDate;

}
