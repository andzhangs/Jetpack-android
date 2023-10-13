package zs.jetpack.versionedparcelable

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelable
import androidx.versionedparcelable.VersionedParcelize

/**
 *
 * @author zhangshuai
 * @date 2023/10/11 19:05
 * @mark 自定义类描述
 */
@VersionedParcelize
class UserBean() : Parcelable, VersionedParcelable {

    var name: String = ""

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserBean> {
        override fun createFromParcel(parcel: Parcel): UserBean {
            return UserBean(parcel)
        }

        override fun newArray(size: Int): Array<UserBean?> {
            return arrayOfNulls(size)
        }
    }


}