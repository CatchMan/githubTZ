package com.catchman.githubtesttask.ui.profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.catchman.domain.model.CommonUser
import com.catchman.githubtesttask.R
import com.catchman.githubtesttask.ui.base.BaseActivity
import javax.inject.Inject
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.content.Context
import android.net.Uri
import android.support.v4.widget.CircularProgressDrawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.catchman.domain.model.UserInfo
import com.catchman.githubtesttask.ui.userList.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_profile.*
import android.support.customtabs.CustomTabsIntent
import android.view.MenuItem


class ProfileActivity : BaseActivity(), ProfileContract.View, View.OnClickListener {

    companion object {

        const val EXTRA_LOGIN = "login"

        fun start(context: Context, login: String) {
            val starter = Intent(context, ProfileActivity::class.java)
            starter.putExtra(EXTRA_LOGIN, login)
            context.startActivity(starter)
        }
    }

    private var userInfo: UserInfo ?= null
    private var login: String = String()

    @Inject
    lateinit var presenter: ProfileContract.Presenter<ProfileContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        activityComponent.inject(this)
        presenter.bindView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onStart() {
        super.onStart()
        login = intent.getStringExtra(EXTRA_LOGIN)
        presenter.getProfile(login)
        tvLink.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun getProfileSuccess(userInfo: UserInfo) {
        this.userInfo = userInfo
        initImage(civProfile, userInfo.avatarUrl)
        tvName.text = userInfo.name
        tvLink.text = userInfo.blog
        tvCountRepos.text = userInfo.publickRepos.toString()
        tvCountGists.text = userInfo.publicGists.toString()
        tvCountFollowers.text = userInfo.followers.toString()
        supportActionBar?.title = userInfo.name
    }

    override fun getProfileFail() {
        Toast.makeText(this, getString(R.string.fail_try_again), Toast.LENGTH_LONG).show()
        onBackPressed()
    }

    override fun onClick(v: View) {
        when(v.id){
            com.catchman.githubtesttask.R.id.tvLink -> {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this, Uri.parse(userInfo?.blog))
            }
        }
    }

    private fun initImage(ivProfile: ImageView, avatarUrl: String){
        Glide
            .with(this)
            .load(avatarUrl)
            .centerCrop()
            .into(ivProfile)
    }
}
