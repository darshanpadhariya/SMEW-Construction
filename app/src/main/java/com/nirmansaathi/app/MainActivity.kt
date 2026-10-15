package com.nirmansaathi.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private var completedDays = 0
    private lateinit var progressText: TextView
    private lateinit var daysLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 24)
            setBackgroundColor(Color.rgb(245, 247, 250))
        }

        val scroll = ScrollView(this)
        scroll.addView(root)

        val title = TextView(this).apply {
            text = "🏠 Nirman Saathi"
            textSize = 28f
            setTextColor(Color.rgb(25, 70, 110))
            gravity = Gravity.CENTER
        }
        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "Aapke ghar ke nirman ka digital saathi"
            textSize = 15f
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 24)
        }
        root.addView(subtitle)

        val house = TextView(this).apply {
            text = "        🏠\n   🧱 🧱 🧱\n   🧱 🚪 🧱\n━━━━━━━━━━"
            textSize = 30f
            gravity = Gravity.CENTER
            setPadding(12, 20, 12, 20)
            setBackgroundColor(Color.WHITE)
        }
        root.addView(house)

        val heading = TextView(this).apply {
            text = "💧 7-Day Curing Tracker"
            textSize = 21f
            setTextColor(Color.rgb(25, 70, 110))
            setPadding(0, 24, 0, 12)
        }
        root.addView(heading)

        progressText = TextView(this).apply {
            textSize = 17f
            setTextColor(Color.DKGRAY)
        }
        root.addView(progressText)

        val progress = ProgressBar(
            this, null, android.R.attr.progressBarStyleHorizontal
        ).apply {
            max = 7
        }
        root.addView(progress, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 24
        ))

        daysLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        root.addView(daysLayout)

        val photoButton = Button(this).apply {
            text = "📸 Construction Photos"
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Photo feature agle update mein add hoga",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        root.addView(photoButton)

        fun refreshTracker() {
            progressText.text =
                "Complete: $completedDays / 7 days"
            progress.progress = completedDays
            daysLayout.removeAllViews()

            for (day in 1..7) {
                val button = Button(this).apply {
                    text = if (day <= completedDays)
                        "✓ Day $day — Completed"
                    else
                        "Day $day — Mark complete"

                    setOnClickListener {
                        if (day == completedDays + 1) {
                            completedDays++
                            refreshTracker()
                        } else if (day > completedDays + 1) {
                            Toast.makeText(
                                this@MainActivity,
                                "Pehle pichhla din complete karein",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                daysLayout.addView(button)
            }
        }

        refreshTracker()
        setContentView(scroll)
    }
}
