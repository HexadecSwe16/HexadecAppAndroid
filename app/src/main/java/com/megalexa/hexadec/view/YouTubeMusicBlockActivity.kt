package com.megalexa.hexadec.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.megalexa.hexadec.R
import com.megalexa.hexadec.model.Workflow
import com.megalexa.hexadec.presenter.BlockPresenter
import com.megalexa.hexadec.presenter.contract.MainContract
import kotlinx.android.synthetic.main.textview.*
import kotlinx.android.synthetic.main.workflow_view.my_toolbar

class YouTubeMusicBlockActivity : AppCompatActivity(),MainContract.BlockView {

    internal lateinit var presenter: MainContract.BlockContract
    private lateinit var newWorkflow :Workflow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtubemusicview)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)
        setPresenter(BlockPresenter(this@YouTubeMusicBlockActivity))
        sBlockbtnSalvab.setOnClickListener {
            val intent=Intent(this@YouTubeMusicBlockActivity, AddBlocksActivity::class.java)
            intent.putExtra("Workflow",newWorkflow)
            startActivity(intent)
        }
        sBlockbtnSalvaw.setOnClickListener {
            newWorkflow=presenter.addBlock(newWorkflow,findViewById<EditText>(R.id.textBlockConf).text.toString(),"YOUTUBEMUSIC")
            //presenter.saveWorkflow(newWorkflow)
            startActivity(Intent(this@YouTubeMusicBlockActivity, ViewActivity::class.java))
        }
        sBlockbtnAnnulla.setOnClickListener {
            val intent=Intent(this@YouTubeMusicBlockActivity, NewWorkflowActivity::class.java)
            intent.putExtra("Workflow",newWorkflow)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.settings) {
            startActivity(Intent(this@YouTubeMusicBlockActivity, YouTubeMusicBlockActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setPresenter(presenter: MainContract.BlockContract) {
        this.presenter=presenter
    }

    override fun onStart() {
        super.onStart()
        newWorkflow = intent.getSerializableExtra("WorkFlow") as Workflow
    }

    private fun startToast(message:String) {
        runOnUiThread {
            Toast.makeText(this@YouTubeMusicBlockActivity,message, Toast.LENGTH_LONG).show()
        }
    }
}