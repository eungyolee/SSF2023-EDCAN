package com.example.softwareshare

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.softwareshare.databinding.ActivityQuestionBinding

class question : AppCompatActivity() {

    private lateinit var binding : ActivityQuestionBinding
    var page = 0
    val title = mutableListOf<String>("내가 가장 좋아하는 계절은?", "내가 가장 좋아하는 게임 장르는?", "내가 좋아하는 코딩언어는?", "내 생일은?", "내가 좋아하는 얼굴상은?")
    val question1 = mutableListOf<String>("봄", "FPS", "C언어", "8월 1일", "강아지상")
    val question2 = mutableListOf<String>("여름", "RPG", "Python", "3월 4일", "고양이상")
    val question3 = mutableListOf<String>("가을", "퍼즐", "Java","12월 23일","여우상")
    val question4 = mutableListOf<String>("겨울", "생존", "HTML", "10월 4일", "곰상")
    val answer = mutableListOf<Int>(3, 2, 1, 4, 3)
    var score = 0
    var clickable = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val personname = intent.getStringExtra("name")
        binding.q1.text = "Q${page+1}.\n${title[page]}"
        binding.b1.text = "1. ${question1[page]}"
        binding.b2.text = "2. ${question2[page]}"

        binding.b3.text = "3. ${question3[page]}"
        binding.b4.text = "4. ${question4[page]}"

        binding.b1.setOnClickListener {
            if (clickable == false)
            {
                if(answer[page] == 1)
                {
                    score++

                    binding.b1.setBackgroundResource(R.drawable.correct_answer)
                    binding.textAnswer.text = "정답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.check)
                }
                else
                {
                    binding.b1.setBackgroundResource(R.drawable.wrong_answer)
                    binding.textAnswer.text = "오답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.close)

                }

                clickable = true
                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE

            }

        }
        binding.b2.setOnClickListener {
            if (clickable == false)
            {
                if(answer[page] == 2)
                {
                    score++
                    binding.b2.setBackgroundResource(R.drawable.correct_answer)
                    binding.textAnswer.text = "정답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.check)

                }
                else
                {
                    binding.b2.setBackgroundResource(R.drawable.wrong_answer)
                    binding.textAnswer.text = "오답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.close)
                }

                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE
                clickable = true
            }
        }
        binding.b3.setOnClickListener {
            if (clickable == false)
            {
                if(answer[page] == 3)
                {
                    score++
                    binding.b3.setBackgroundResource(R.drawable.correct_answer)
                    binding.textAnswer.text = "정답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.check)


                }
                else
                {
                    binding.b3.setBackgroundResource(R.drawable.wrong_answer)
                    binding.textAnswer.text = "오답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.close)


                }

                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE
                clickable=true
            }
        }
        binding.b4.setOnClickListener {
            if (clickable == false) {
                if (answer[page] == 4) {
                    score++
                    binding.b4.setBackgroundResource(R.drawable.correct_answer)
                    binding.textAnswer.text = "정답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.check)


                } else {
                    binding.b4.setBackgroundResource(R.drawable.wrong_answer)
                    binding.textAnswer.text = "오답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.close)

                }

                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE
                clickable = true
            }
        }
        binding.submit.setOnClickListener {

            page++

            if (page == title.size)
            {
                //파이어베이스 정보 저장
                //점수 변수는 score

                val intent = Intent(this, ranking::class.java)
                intent.putExtra("score", score)                           //성적은 score로 보냄
                startActivity(intent)
                this.finish()
            }
            else
            {
                binding.q1.text = "Q${page+1}.\n${title[page]}"
                binding.b1.text = "1. ${question1[page]}"
                binding.b2.text = "2. ${question2[page]}"

                binding.b3.text = "3. ${question3[page]}"
                binding.b4.text = "4. ${question4[page]}"

                binding.b1.setBackgroundResource(R.drawable.questbutton)
                binding.b2.setBackgroundResource(R.drawable.questbutton)
                binding.b3.setBackgroundResource(R.drawable.questbutton)
                binding.b4.setBackgroundResource(R.drawable.questbutton)

                binding.imgAnswer.visibility = View.INVISIBLE
                binding.textAnswer.visibility = View.INVISIBLE
                clickable = false

                if (page == title.size-1)
                {
                    binding.submit.text = "제출하기"
                }
            }

        }


    }
}