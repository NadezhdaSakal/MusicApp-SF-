package com.sakal.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var audioAdapter: AudioListRecyclerAdapter
    val audioDB = listOf(
        Audio(R.drawable.ava1, "Rammstein","Sonne"),
        Audio(R.drawable.ava2, "Мельница","Ночная кобыла"),
        Audio(R.drawable.ava3, "Nightwish", "She is my sin"),
        Audio(R.drawable.ava4, "Louna","Штурмуя небеса"),
        Audio(R.drawable.ava5, "Король и Шут","Прыгну со скалы"),
        Audio(R.drawable.ava6, "Порнофильмы","Я так соскучился"),
        Audio(R.drawable.ava7, "Ария","Осколок льда"),
        )

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()

        //находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пысты, он нам понадобится во второй части задания
            audioAdapter = AudioListRecyclerAdapter(object : AudioListRecyclerAdapter.OnItemClickListener{
                override fun click(audio: Audio) {
                    //Создаем бандл и кладем туда объект с данными фильма
                    val bundle = Bundle()
                    //Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                    //передаваемы объект
                    bundle.putParcelable("audio", audio)
                    //Запускаем наше активити
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    //Прикрепляем бандл к интенту
                    intent.putExtras(bundle)
                    //Запускаем активити через интент
                    startActivity(intent)
                }
            })
            //Присваиваем адаптер
            adapter = audioAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(this@MainActivity)
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        audioAdapter.addItems(audioDB)
    }

    private fun initNavigation() {
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id._latest -> {
                    Toast.makeText(this, "Поcледнее", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.playlist -> {
                    Toast.makeText(this, "Плэйлисты", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

       // bottom_navigation.setOnNavigationItemSelectedListener {


        }
    }

