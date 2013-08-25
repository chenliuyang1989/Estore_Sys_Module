package com.gmcc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractVersionnotice;


/**
 * @title Versionnotice.java
 * @desc com.gmcc.estore.wms.model
 * @company www.ibm.com.cn
 * @date Jul 13, 2011 
 * @author Shine.He
 * @version 1.4
 */

@Entity
@Table(name="T_SYSTEM_NOTICE"

)
public class Versionnotice extends AbstractVersionnotice implements Serializable {

}
