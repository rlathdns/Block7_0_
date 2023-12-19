package com.cookandroid.block7

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings.Global
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView


class MainPage : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        startService(Intent(this, MusicService::class.java))

        // 버튼을 클릭 시 이미지의 변환을 주기 위한 선언.
        val startGameImage: ImageView = findViewById(R.id.basic_gamestart)
        val ClickedStartImage: ImageView = findViewById(R.id.selected_gamestart)
        val OptionImage: ImageView = findViewById(R.id.basic_option)
        val ClikedOptionImage: ImageView = findViewById(R.id.selected_option)
        val exitImage: ImageView= findViewById(R.id.basic_exit)
        val ClikedExitImage: ImageView = findViewById(R.id.selected_exit)
        val moreImage: ImageView= findViewById(R.id.basic_explain)
        val ClikedMoreImage: ImageView = findViewById(R.id.selected_game_Intro)

        // 게임 시작 버튼에 클릭 리스너 추가
        val startGameFrame : ImageButton = findViewById(R.id.startGame)
        startGameFrame.setOnClickListener {
            click_sound()
            startGameImage.visibility = View.INVISIBLE
            ClickedStartImage.visibility = View.VISIBLE
            startGameFrame.postOnAnimationDelayed({
                startGameImage.visibility = View.VISIBLE
                ClickedStartImage.visibility = View.INVISIBLE
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)},400)

        }

        // 옵션 이미지에 클릭 리스너 추가
        val optionButton: ImageButton = findViewById(R.id.option_game)
        optionButton.setOnClickListener {
            click_sound()
            OptionImage.visibility = View.INVISIBLE
            ClikedOptionImage.visibility = View.VISIBLE
            startGameFrame.postOnAnimationDelayed({
                OptionImage.visibility = View.VISIBLE
                ClikedOptionImage.visibility = View.INVISIBLE
            val intent = Intent(this, OptionDialog::class.java)
            startActivity(intent)},400)
        }
        val moreButton: ImageButton = findViewById(R.id.achieve)
        moreButton.setOnClickListener {
            click_sound()
            moreImage.visibility = View.INVISIBLE
            ClikedMoreImage.visibility = View.VISIBLE
            startGameFrame.postOnAnimationDelayed({
                moreImage.visibility = View.VISIBLE
                ClikedMoreImage.visibility = View.INVISIBLE
                val intent = Intent(this, MoreDialog::class.java)
                startActivity(intent)},400)
        }

        // 종료 이미지에 클릭 리스너 추가
        val exitFrame : ImageButton = findViewById(R.id.Cancel)
        exitFrame.setOnClickListener {
            click_sound()
            exitImage.visibility = View.INVISIBLE
            ClikedExitImage.visibility = View.VISIBLE
            startGameFrame.postOnAnimationDelayed({
                exitImage.visibility = View.VISIBLE
                ClikedExitImage.visibility = View.INVISIBLE
                finishAffinity()

            },400)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MusicService::class.java))
    }

    fun click_sound(){
        val music_intent = Intent(this, MusicService::class.java)
        music_intent.action = "PLAY_CLICK_SOUND"
        startService(music_intent)
    }
}

