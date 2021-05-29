package com.yello.emitter.user.presentation.activities

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yello.emitter.R
import com.yello.emitter.helper.OnItemAdapterClickListener
import com.yello.emitter.receiver.UserBroadcastReceiver
import com.yello.emitter.user.domain.model.User
import com.yello.emitter.user.presentation.adapters.UserAdapter
import com.yello.emitter.user.presentation.viewModel.UserViewModel
import com.yello.emitter.user.presentation.viewModel.state.UserVS
import kotlinx.android.synthetic.main.activity_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UsersActivity : AppCompatActivity(), OnItemAdapterClickListener<User> {
    private val mViewModel: UserViewModel by viewModel()
    private val mAdapter = UserAdapter(this)
    private val mUserReceiver = UserBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        intent.getParcelableExtra<User>("user")?.let { doneDialog() }

        mViewModel.viewState.observe(this) {
            when (it) {
                is UserVS.AddUser -> {
                    refresh.isRefreshing = false
                    mAdapter.addUser(it.user)
                }
            }
        }

        userRecyclerView()
        mViewModel.users()

        refresh.setOnRefreshListener {
            mAdapter.clearItems()
            mViewModel.users()
        }
        val intentFilter = IntentFilter("com.yello.emitter.User")
        registerReceiver(mUserReceiver, intentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(mUserReceiver)
        super.onDestroy()
    }

    private fun userRecyclerView() {
        refresh.isRefreshing = true
        rvUsers?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun doneDialog() {
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(this@UsersActivity)
        builder.setMessage(getString(R.string.save_user))
        builder.setPositiveButton("ok") { dialog, _ ->
            dialog.dismiss()
        }
        dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun onItemAdapterClicked(item: User) {
        val intent = Intent()
        intent.putExtra("user", Gson().toJson(item))
        intent.action = "com.yello.middleman.User"
        sendBroadcast(intent)
    }
}