package org.example.buyticket.app.model.entity.person;

import org.example.buyticket.app.model.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity that encapsulates user of the application
 * @author Gulyamov Rustam
 */
@Table(name="ACCOUNT")
@Entity
public class Account extends AbstractEntity {

}
