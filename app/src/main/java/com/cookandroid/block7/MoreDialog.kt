package com.cookandroid.block7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MoreDialog:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_dialog)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val exit= findViewById<Button>(R.id.close_more)
        adapter.addFragment(TabFragment.newInstance("게임설명"), "게임설명")
        adapter.addFragment(TabFragment.newInstance("캐릭터 소개"), "캐릭터 소개")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_tab_unselected)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_tab_unselected)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.ic_tab_selected)
                    1 -> tab.setIcon(R.drawable.ic_tab_selected)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Reset the icon to unselected state for all tabs
                for (i in 0 until tabLayout.tabCount) {
                    val tabAt = tabLayout.getTabAt(i)
                    tabAt?.setIcon(R.drawable.ic_tab_unselected)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Not used in this example
            }
        })
        exit.setOnClickListener{
            click_sound()
            finish()
        }

    }

    class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList = mutableListOf<Fragment>()
        private val fragmentTitleList = mutableListOf<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
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