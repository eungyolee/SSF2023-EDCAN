package com.example.softwareshare

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.softwareshare.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore

class question : AppCompatActivity() {

    private lateinit var binding : ActivityQuestionBinding
    var page = 0
    val title = mutableListOf<String>("내가 가장 좋아하는 계절은?", "내가 다니는 학교는?", "내가 좋아하는 코딩언어는?", "내 생일은?", "아무거나 찍어보면?")
    val question1 = mutableListOf<String>("봄", "선린중학교", "C언어", "8월 1일", "3")
    val question2 = mutableListOf<String>("여름", "염경중학교", "Python", "3월 4일", "1")
    val question3 = mutableListOf<String>("가을", "염창중학교", "JavaScript","12월 23일","4")
    val question4 = mutableListOf<String>("겨울", "백석중학교", "Kotlin", "10월 4일", "2")
    val answer = mutableListOf<Int>(4, 2, 2, 3, 1)
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

                clickable = true
                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE

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

                clickable = true
                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE

            }

        }
        binding.b4.setOnClickListener {
            if (clickable == false)
            {
                if(answer[page] == 4)
                {
                    score++

                    binding.b4.setBackgroundResource(R.drawable.correct_answer)
                    binding.textAnswer.text = "정답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.check)
                }
                else
                {
                    binding.b4.setBackgroundResource(R.drawable.wrong_answer)
                    binding.textAnswer.text = "오답입니다."
                    binding.imgAnswer.setBackgroundResource(R.drawable.close)

                }

                clickable = true
                binding.imgAnswer.visibility = View.VISIBLE
                binding.textAnswer.visibility = View.VISIBLE

            }
        }
        binding.submit.setOnClickListener {

            page++

            if (page == title.size)
            {
                val intent = Intent(this, ranking::class.java)
                val db = FirebaseFirestore.getInstance()

                val collectionName = "rankings"
                val documentName = "nameandscore"
                intent.putExtra("personscore", score.toString())                           //성적은 score로 보냄
                intent.putExtra("personname", personname)
                val data = hashMapOf(
                    "name" to personname,
                    "score" to score
                )

                db.collection(collectionName)
                    .document()
                    .set(data)
                    .addOnSuccessListener {
                        // 저장 성공 시 실행할 코드
                        // 예: 저장 완료 메시지 출력
                        Log.d("TAG", "데이터 저장 완료")
                    }
                    .addOnFailureListener { e ->
                        // 저장 실패 시 실행할 코드
                        // 예: 저장 실패 메시지 출력
                        Log.w("TAG", "데이터 저장 실패", e)
                    }

                //파이어베이스 정보 저장
                //점수 변수는 score


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