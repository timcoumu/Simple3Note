package com.timcoumu.simple3note

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.MaterialToolbar
import com.timcoumu.mydirary.database.Diary
import com.timcoumu.simple3note.Controller.Controller
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Callback {
    lateinit var toolbar: MaterialToolbar
    lateinit var mainFragment: MainFragment
    lateinit var editFragment: EditFragment
    lateinit var transiention : FragmentTransaction
    lateinit var controller: Controller
    var isMainFragment: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        controller = Controller(this)
        toolbar = findViewById(R.id.material_toolbar)
        setSupportActionBar(toolbar)
        mainFragment = MainFragment(controller.loadtoMainfragment())
        //首次启动创建fragment实例
        addFragment(mainFragment)
    }
    /**添加fragment方法
     *
     */
    private fun addFragment(fragment: Fragment) {
        transiention = supportFragmentManager.beginTransaction()
        transiention
            .add(R.id.fragment_container, fragment)
            .commit()
        when(fragment){
            mainFragment -> isMainFragment = true
            editFragment -> isMainFragment = false
            else -> isMainFragment = true
        }
    }
    /**移除fragment方法
     *
     */
    private fun removeFragment(fragment: Fragment) {
        transiention = supportFragmentManager.beginTransaction()
        transiention
            .remove(fragment)
            .commit()
    }
    //设置toolbar,每次更新页面时都会调用
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isMainFragment) {
            menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        }else{
            menuInflater.inflate(R.menu.menu_toolbar_edit, menu)
        }
        return true
    }
    //设置toolbar点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean  {
        when(item.itemId){
            R.id.meun_add -> {
                editFragment = EditFragment()
                addFragment(editFragment)
                removeFragment(mainFragment)
            }
            R.id.meun_save ->{
                val diary: Diary = editFragment.saveEdit()
                if(controller.savetoDatabase(diary.title, diary.details)) {
                    mainFragment = MainFragment(controller.loadtoMainfragment())
                    addFragment(mainFragment)
                    removeFragment(editFragment)
                }
            }
            R.id.meun_delete -> {
                mainFragment = MainFragment(controller.loadtoMainfragment())
                addFragment(mainFragment)
                removeFragment(editFragment)
            }
        }
        return true
    }
    //设置返回事件
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("未保存内容将会被删除")
            .setTitle("退出？")
            .setPositiveButton("退出") { dialog, id ->
                super.onBackPressed()
            }
            .setNegativeButton("取消") { dialog, id ->
            }
            .show()
    }
    //回调到这里，当点击recyclerview中的一项时，触发操作
    override fun itemClicked(title: String) {
        removeFragment(mainFragment)
        editFragment = EditFragment()
        addFragment(editFragment)
        GlobalScope.launch {
            delay(50)
            val oneDiary = controller.loadOneData(title)
            editFragment.loadtoEdit(oneDiary.title, oneDiary.details)
            controller.deleteOneData(title)
        }
    }
}