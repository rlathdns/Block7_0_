package com.cookandroid.block7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cookandroid.block7.R  // Make sure to import your R class correctly

class TabFragment : Fragment() {

    private var tabTitle: String? = null
    private lateinit var textView: TextView  // Declare textView as lateinit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabTitle = it.getString(ARG_TAB_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize textView here
        textView = view.findViewById(R.id.textView)

        // Set up your fragment content here
        tabTitle?.let { title ->
            textView.text = title
        }
    }

    companion object {
        private const val ARG_TAB_TITLE = "tab_title"

        fun newInstance(tabTitle: String): TabFragment {
            val fragment = TabFragment()
            val args = Bundle()
            args.putString(ARG_TAB_TITLE, tabTitle)
            fragment.arguments = args
            return fragment
        }
    }
}
