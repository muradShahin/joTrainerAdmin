package com.murad.jotraineradmin.Enities

import android.os.Parcel
import android.os.Parcelable

class Trainees():Parcelable {

    private var studentId = 0

    fun getStudentId(): Int {
        return studentId
    }

    fun setStudentId(studentId: Int) {
        this.studentId = studentId
    }

    private var email: String? = null
    private var fname: String? = null
    private var lname: String? = null
    private var profileImg: String? = null
    private var age: String? = null
    private var lat: String? = null
    private var lng: String? = null
    private var phone: String? = null
    private var city: String? = null
    private var Date: String? = null
    private var lessons: String? = null

    fun getRate(): Int {
        return rate
    }

    fun setRate(rate: Int) {
        this.rate = rate
    }

    private var rate = 0
    private var lessons_left = 0

    fun getLessons_left(): Int {
        return lessons_left
    }

    fun setLessons_left(lessons_left: Int) {
        this.lessons_left = lessons_left
    }

    private var TeacherName: String? = null

    constructor(parcel: Parcel) : this() {
        studentId = parcel.readInt()
        email = parcel.readString()
        fname = parcel.readString()
        lname = parcel.readString()
        profileImg = parcel.readString()
        age = parcel.readString()
        lat = parcel.readString()
        lng = parcel.readString()
        phone = parcel.readString()
        city = parcel.readString()
        Date = parcel.readString()
        lessons = parcel.readString()
        rate = parcel.readInt()
        lessons_left = parcel.readInt()
        TeacherName = parcel.readString()
    }

    fun getDate(): String? {
        return Date
    }

    fun setDate(date: String?) {
        Date = date
    }

    fun getLessons(): String? {
        return lessons
    }

    fun setLessons(lessons: String?) {
        this.lessons = lessons
    }

    fun getTeacherName(): String? {
        return TeacherName
    }

    fun setTeacherName(teacherName: String?) {
        TeacherName = teacherName
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(studentId)
        parcel.writeString(email)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(profileImg)
        parcel.writeString(age)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeString(phone)
        parcel.writeString(city)
        parcel.writeString(Date)
        parcel.writeString(lessons)
        parcel.writeInt(rate)
        parcel.writeInt(lessons_left)
        parcel.writeString(TeacherName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trainees> {
        override fun createFromParcel(parcel: Parcel): Trainees {
            return Trainees(parcel)
        }

        override fun newArray(size: Int): Array<Trainees?> {
            return arrayOfNulls(size)
        }
    }

}