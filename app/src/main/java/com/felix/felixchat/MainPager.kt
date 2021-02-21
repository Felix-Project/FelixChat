package com.felix.felixchat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.felix.chat.chatlist.ChatListFragment
import com.felix.contact.ContactFragment

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: MainPager
 */
class MainPager(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChatListFragment()
            1 -> ContactFragment()
            2 -> ChatListFragment()
            3 -> ContactFragment()
            else -> Fragment()
        }
    }


}