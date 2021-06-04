package com.example.retrofitoperationapp.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitoperationapp.R
import com.example.retrofitoperationapp.data.Data
import com.example.retrofitoperationapp.util.loadImage
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(private var users: ArrayList<Data>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
//class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    private val email = view.email
        private val name = view.first_name
        private val imageView = view.imageView


        fun bind(data: Data) {

            email.text = data.email
            name.text = data.first_name
            imageView.loadImage(data.avatar)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemUserBinding.inflate(layoutInflater)
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.setOnClickListener{
            val action = UserListFragmentDirections.actionUserListFragmentToDetailFragment2(user = users[position])
            Navigation.findNavController(it).navigate(action)
        }
        holder.bind(this.users[position])

    }

    override fun getItemCount(): Int {
        return this.users.size
    }

    fun setUser(data: List<Data>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }


}