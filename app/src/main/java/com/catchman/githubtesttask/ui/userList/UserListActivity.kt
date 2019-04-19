package com.catchman.githubtesttask.ui.userList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.catchman.domain.model.CommonUser
import com.catchman.githubtesttask.R
import com.catchman.githubtesttask.ui.base.BaseActivity
import com.catchman.githubtesttask.ui.profile.ProfileActivity
import com.catchman.githubtesttask.ui.userList.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject








class UserListActivity : BaseActivity(), UserListContract.View, View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private var userAdapter: UserAdapter ?= null
    private var commonUsers: ArrayList<CommonUser> ?= null
    private var isLoading = false
    @Inject
    lateinit var presenter: UserListContract.Presenter<UserListContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        activityComponent.inject(this)
        presenter.bindView(this)
        initUI()
    }

    override fun onRefresh() {
        presenter.loadUsers(0)
    }



    override fun getUsersSuccess(users: List<CommonUser>) {
        if(commonUsers == null || commonUsers!!.isEmpty()){
            userAdapter?.updateData(users)
            commonUsers = ArrayList(users)
        } else {
            userAdapter?.addData(users)
            commonUsers?.addAll(users)
        }
        isLoading = false
        srlContainer.isRefreshing = false
    }

    override fun getUsersFail() {
        srlContainer.isRefreshing = false
        isLoading = false
    }

    override fun onClick(v: View) {
        var position: Int = rvUsers!!.getChildAdapterPosition(v)
        ProfileActivity.start(this, commonUsers!![position].login)
    }


    private fun initUI() {
        supportActionBar?.title = getString(R.string.users)
        userAdapter = UserAdapter(this)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = userAdapter
        showProgress(true)
        presenter.loadUsers(commonUsers?.size?.toLong()?:0)
        srlContainer.setOnRefreshListener(this)
        initScrollListener()
    }

    private fun initScrollListener() {
        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == commonUsers!!.size - 1) {
                        //bottom of list!
                        presenter.loadUsers(commonUsers!!.size.toLong())
                        isLoading = true
                    }
                }
            }
        })


    }
}
