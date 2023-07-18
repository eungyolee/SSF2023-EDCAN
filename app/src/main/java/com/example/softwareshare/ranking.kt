package com.example.softwareshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.softwareshare.databinding.ActivityQuestionBinding
import com.example.softwareshare.databinding.ActivityRankingBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ranking : AppCompatActivity() {

    private lateinit var binding : ActivityRankingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val personname = intent.getStringExtra("personname")
        val personscore = intent.getStringExtra("personscore")

        binding.scoretext.text = "당신의 점수는 ${personscore}점 입니다!"


        val db = FirebaseFirestore.getInstance()
        // 데이터를 불러올 컬렉션 이름 설정
        val collectionName = "rankings"

// 랭킹 데이터를 불러오는 쿼리 작성 및 실행
        db.collection(collectionName)
            .orderBy("score", Query.Direction.DESCENDING)
            .limit(3) // 상위 10개의 랭킹 데이터만 가져옴
            .get()
            .addOnSuccessListener { querySnapshot ->
                // 불러오기 성공 시 실행할 코드
                // 예: 데이터를 사용하여 랭킹을 표시
                val rankings = querySnapshot.documents
                for (i in rankings.indices) {
                    val rank = i + 1
                    val document = rankings[i]
                    val name = document.getString("name")
                    val score = document.getLong("score")?.toInt()
                    if (rank == 1)
                    {
                        binding.rank1.text = "${rank}. ${name}"

                        binding.rank1Score.text="${score}점"
                    }
                    if (rank == 2)
                    {
                        binding.rank2.text = "${rank}. ${name}"

                        binding.rank2Score.text="${score}점"
                    }
                    if (rank == 3)
                    {
                        binding.rank3.text = "${rank}. ${name}"
                        binding.rank3Score.text="${score}점"
                    }
                    Log.d("TAG", "순위: $rank, 이름: $name, 점수: $score")
                }
            }
            .addOnFailureListener { e ->
                // 불러오기 실패 시 실행할 코드
                // 예: 불러오기 실패 메시지 출력
                Log.w("TAG", "데이터 불러오기 실패", e)
            }
        binding.returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }


    }
}