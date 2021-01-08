package com.murad.jotraineradmin.Enities

import android.os.Parcel
import android.os.Parcelable

class Trainers() :Parcelable{


    private var id = 0
    private var rate: String? = null
    private var email: String? = null
    private var fname: String? = null
    private var lname: String? = null
    private var password: String? = null
    private var profileImg: String? = null
    private var age: String? = null
    private var lat: String? = null
    private var lng: String? = null
    private var phone: String? = null
    private var city: String? = null
    private var isApproved:String?=null
    private var certificate:String?=null



    fun getRate(): String? {
        return rate
    }

    fun setRate(rate: String?) {
        this.rate = rate
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getFname(): String? {
        return fname
    }

    fun setFname(fname: String?) {
        this.fname = fname
    }

    fun getLname(): String? {
        return lname
    }

    fun setLname(lname: String?) {
        this.lname = lname
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getProfileImg(): String? {
        return profileImg
    }

    fun setProfileImg(profileImg: String?) {
        this.profileImg = profileImg
    }

    fun getAge(): String? {
        return age
    }

    fun setAge(age: String?) {
        this.age = age
    }

    fun getLat(): String? {
        return lat
    }

    fun setLat(lat: String?) {
        this.lat = lat
    }

    fun getLng(): String? {
        return lng
    }

    fun setLng(lng: String?) {
        this.lng = lng
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

    fun getRole(): String? {
        return role
    }

    fun setRole(role: String?) {
        this.role = role
    }

    private var role: String? = null
    private var exp: String? = null
    private var carType: String? = null
    private var carImg: String? = null
    private var office: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        rate = parcel.readString()
        email = parcel.readString()
        fname = parcel.readString()
        lname = parcel.readString()
        password = parcel.readString()
        profileImg = parcel.readString()
        age = parcel.readString()
        lat = parcel.readString()
        lng = parcel.readString()
        phone = parcel.readString()
        city = parcel.readString()
        isApproved = parcel.readString()
        role = parcel.readString()
        exp = parcel.readString()
        carType = parcel.readString()
        carImg = parcel.readString()
        office = parcel.readString()
    }

    fun getExp(): String? {
        return exp
    }

    fun setExp(exp: String?) {
        this.exp = exp
    }

    fun getCarType(): String? {
        return carType
    }

    fun setCarType(carType: String?) {
        this.carType = carType
    }

    fun getCarImg(): String? {
        return carImg
    }

    fun setCarImg(carImg: String?) {
        this.carImg = carImg
    }

    fun getOffice(): String? {
        return office
    }

    fun setOffice(office: String?) {
        this.office = office
    }

    fun setId(id:Int){
        this.id=id
    }

    fun getId():Int{
        return this.id
    }

    fun setIsApproved(isApproved:String){
        this.isApproved=isApproved
    }

    fun getIsApproved():String?{
       return this.isApproved
    }

    fun setCertificate(cert:String){
        this.certificate=cert
    }

    fun getCertificate():String{

        return this.certificate!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(rate)
        parcel.writeString(email)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(password)
        parcel.writeString(profileImg)
        parcel.writeString(age)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeString(phone)
        parcel.writeString(city)
        parcel.writeString(isApproved)
        parcel.writeString(role)
        parcel.writeString(exp)
        parcel.writeString(carType)
        parcel.writeString(carImg)
        parcel.writeString(office)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trainers> {
        override fun createFromParcel(parcel: Parcel): Trainers {
            return Trainers(parcel)
        }

        override fun newArray(size: Int): Array<Trainers?> {
            return arrayOfNulls(size)
        }
    }

}