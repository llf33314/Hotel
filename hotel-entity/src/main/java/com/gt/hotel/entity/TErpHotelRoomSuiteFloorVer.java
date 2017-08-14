package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;

import lombok.Data;

/**
 * <p>
 * ERP酒店房型楼层房间 - 楼层
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@Data
public class TErpHotelRoomSuiteFloorVer extends Model<TErpHotelRoomSuiteFloorVer> {

    private static final long serialVersionUID = 1L;
    
    private Integer id;

	private String floor;
	
	private List<TErpHotelRoomSuite> tErpHotelRoomSuites;

	public TErpHotelRoomSuiteFloorVer(String floor) {
		this.floor = floor;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public List<TErpHotelRoomSuite> gettErpHotelRoomSuites() {
		return tErpHotelRoomSuites;
	}

	public void settErpHotelRoomSuites(List<TErpHotelRoomSuite> tErpHotelRoomSuites) {
		this.tErpHotelRoomSuites = tErpHotelRoomSuites;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomSuiteFloorVer [id=" + id + ", floor=" + floor + ", tErpHotelRoomSuites="
				+ tErpHotelRoomSuites + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TErpHotelRoomSuiteFloorVer other = (TErpHotelRoomSuiteFloorVer) obj;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		return true;
	}
	
	
}
