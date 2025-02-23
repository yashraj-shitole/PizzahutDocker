package com.PizzaHut.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PizzaHut.dao.AddressDao;
import com.PizzaHut.dto.AddressDto;
import com.PizzaHut.dto.DtoEntityConvertor;
import com.PizzaHut.entities.Address;
import com.PizzaHut.entities.Users;

@Service
@Transactional
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private DtoEntityConvertor convertor;
	
	//findByAddressId
	public Address getAddressById(int id) {
		System.out.println("In service");
		Address getAddressById = addressDao.getById(id);
		 if(getAddressById != null) {
			 return getAddressById;
		 }
		 return null;
	}
	
	//save address of a users
	public Address addAddress(AddressDto addressdto) {
		Address add = convertor.toAddressEntity(addressdto);
		
		return addressDao.save(add);
	}
	
	//Update address of a users
	public Address updateAddress(int AddressId, AddressDto addressdto) {
		Address addressById =  getAddressById(AddressId);
		if(addressById != null){
			Address updatedAddress = convertor.toAddressEntity(addressdto);
			updatedAddress.setAddressId(addressById.getAddressId());
			System.out.println("--->"+updatedAddress);
			addressDao.save(updatedAddress);
			return updatedAddress;
		}
		return null;
	}
	
	// to get addresses of user
	public List<Address> getAddresses(int userid){
		Address toGetAddress = new Address();
		Users getUserAdd= new Users();
		getUserAdd.setUserId(userid);
		toGetAddress.setUser(getUserAdd);
		List<Address> getFinalAddress = addressDao.findByUser(getUserAdd);
		return getFinalAddress;
	}
	
	//delete certain address
	public int deleteByAddressId(int addressId) {
		addressDao.deleteById(addressId);
		return 1;
	}
}

//public List<Address> getAddresses(int userid){
//	Address toGetAddress = new Address();
//	Users getUserAdd= new Users();
//	getUserAdd.setUserId(userid);
//	toGetAddress.setUser(getUserAdd);
//	List<Address> getFinalAddress = addressDao.findByUser(getUserAdd);
//		return getFinalAddress;
//}







