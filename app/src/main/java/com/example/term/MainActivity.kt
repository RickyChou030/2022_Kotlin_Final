package com.example.term

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    private val equation = ArrayList<String>()
    private var line = 0
    private val answer = ansGenerate()

    private fun ansGenerate(): ArrayList<String> {
        val ans = ArrayList<String>()
        var ansString = ""
        var a1: Int
        var a2: Int
        var a3 = 0
        var operator = ""
        val operatorNum = (1..4).random()
        var done = false

        while (!done) {
            a1 = (0..99).random()
            a2 = (0..99).random()
            when (operatorNum) {
                1 -> {
                    a3 = a1 + a2
                    operator = "+"
                }
                2 -> {
                    a3 = a1 - a2
                    operator = "-"
                }
                3 -> {
                    a3 = a1 * a2
                    operator = "*"
                }
                4 -> {
                    if(a1 % a2 != 0) continue
                    a3 = a1 / a2
                    operator = "/"
                }
            }
            ansString = "$a1$operator$a2=$a3"

            if (ansString.length == 6 && a3 >= 0) {
                for (i in 0..5) {
                    ans.add(ansString[i].toString())
                }
                done = true
            } else continue
        }
        println("answer = $ans")
        return ans
    }

    private fun push(x: String) {
        val builder = AlertDialog.Builder(this)

        if (equation.size != 6) equation.add(x)
        else {
            builder.setTitle("Error")
            builder.setMessage("Max digit = 6")
            builder.show()
            return
        }
    }

    private fun pop() {
        if (equation.isNotEmpty()) equation.removeLast()
    }

    private fun calculate(n1: String, n2: String, n3: String, operator: String): Boolean {
        when (operator) {
            "+" -> return n1.toInt() + n2.toInt() == n3.toInt()
            "-" -> return n1.toInt() - n2.toInt() == n3.toInt()
            "*" -> return n1.toInt() * n2.toInt() == n3.toInt()
            "/" -> if(n1.toInt() % n2.toInt() == 0)return n1.toInt() / n2.toInt() == n3.toInt()
        }
        return false
    }

    private fun judge() {
        val builder = AlertDialog.Builder(this)

        var operators = 0
        var equal = false
        var leading = false
        var valid = true

        var n1 = ""
        var n2 = ""
        var n3 = ""
        var operator = ""

        if (equation.size == 6) {
            for (i in 0..5) {
                if (!(equation[i] in "0".."9")) {
                    if (equal) {
                        builder.setTitle("Error")
                        builder.setMessage("No more operators behind =")
                        builder.show()
                        return
                    }
                    if (equation[i] == "=") equal = true
                    else operator += equation[i]
                    operators++
                } else {
                    if (equal) {
                        if (n3 == "0") leading = true
                        n3 += equation[i]
                    } else if (operators > 0) {
                        if (n2 == "0") leading = true
                        n2 += equation[i]
                    } else {
                        if (n1 == "0") leading = true
                        n1 += equation[i]
                    }
                }
            }
        } else {
            builder.setTitle("Error")
            builder.setMessage("Input more digits")
            builder.show()
            return
        }

        if (!equal) {
            builder.setTitle("Error")
            builder.setMessage("Where is your = ?")
            builder.show()
            return
        } else if (operators == 1) {
            builder.setTitle("Error")
            builder.setMessage("Where is your operator ?")
            builder.show()
            return
        } else if (operators > 2) {
            builder.setTitle("Error")
            builder.setMessage("Too many operators !!")
            builder.show()
            return
        }

        if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty()) {
            builder.setTitle("Error")
            builder.setMessage("Missing numbers !!")
            builder.show()
            return
        }

        if (n2 == "0" && operator == "/") {
            builder.setTitle("Error")
            builder.setMessage("Cannot divide 0 !!")
            builder.show()
            return
        }

        if (leading) {
            builder.setTitle("Error")
            builder.setMessage("No leading zero !!")
            builder.show()
            return
        }

        if (calculate(n1, n2, n3, operator)) {
            for (i in 0..5) {
                if (answer.contains(equation[i])) {
                    if (equation[i] != answer[i]) {
                        valid = false
                        color(i, "#820458")
                    } else {
                        color(i, "#398874")
                    }
                } else {
                    valid = false
                    color(i, "#161803")
                }
            }

            if (valid) {
                builder.setTitle("Congrats")
                builder.setMessage("Congratulations !!")
                builder.show()
                return
            } else {
                line++
                equation.clear()
                if (line == 6) {
                    builder.setTitle("Fail")
                    builder.setMessage("You have run out of lines !! The answer is $answer")
                    builder.show()
                }
                builder.setTitle("Wrong")
                builder.setMessage("Not the same equation !!")
                builder.show()

            }
        } else {
            builder.setTitle("Error")
            builder.setMessage("Invalid equation !!")
            builder.show()
            return
        }
    }

    private fun color(index: Int, rgb: String) {
        when (line) {
            0 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView1).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView2).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView3).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView4).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView5).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView6).setBackgroundColor(Color.parseColor(rgb))
            }
            1 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView7).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView8).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView9).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView10).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView11).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView12).setBackgroundColor(Color.parseColor(rgb))
            }
            2 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView13).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView14).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView15).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView16).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView17).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView18).setBackgroundColor(Color.parseColor(rgb))
            }
            3 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView19).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView20).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView21).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView22).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView23).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView24).setBackgroundColor(Color.parseColor(rgb))
            }
            4 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView25).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView26).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView27).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView28).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView29).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView30).setBackgroundColor(Color.parseColor(rgb))
            }
            5 -> when (index) {
                0 -> findViewById<TextView>(R.id.textView31).setBackgroundColor(Color.parseColor(rgb))
                1 -> findViewById<TextView>(R.id.textView32).setBackgroundColor(Color.parseColor(rgb))
                2 -> findViewById<TextView>(R.id.textView33).setBackgroundColor(Color.parseColor(rgb))
                3 -> findViewById<TextView>(R.id.textView34).setBackgroundColor(Color.parseColor(rgb))
                4 -> findViewById<TextView>(R.id.textView35).setBackgroundColor(Color.parseColor(rgb))
                5 -> findViewById<TextView>(R.id.textView36).setBackgroundColor(Color.parseColor(rgb))
            }
        }
    }

    private fun update() {
        when (line) {
            0 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView1).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView2).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView3).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView4).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView5).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView6).text = equation[5]
            }
            1 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView7).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView8).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView9).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView10).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView11).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView12).text = equation[5]
            }
            2 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView13).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView14).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView15).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView16).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView17).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView18).text = equation[5]
            }
            3 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView19).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView20).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView21).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView22).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView23).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView24).text = equation[5]
            }
            4 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView25).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView26).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView27).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView28).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView29).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView30).text = equation[5]
            }
            5 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView31).text = equation[0]
                2 -> findViewById<TextView>(R.id.textView32).text = equation[1]
                3 -> findViewById<TextView>(R.id.textView33).text = equation[2]
                4 -> findViewById<TextView>(R.id.textView34).text = equation[3]
                5 -> findViewById<TextView>(R.id.textView35).text = equation[4]
                6 -> findViewById<TextView>(R.id.textView36).text = equation[5]
            }
        }
    }

    private fun popUpdate() {
        when (line) {
            0 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView1).text = ""
                2 -> findViewById<TextView>(R.id.textView2).text = ""
                3 -> findViewById<TextView>(R.id.textView3).text = ""
                4 -> findViewById<TextView>(R.id.textView4).text = ""
                5 -> findViewById<TextView>(R.id.textView5).text = ""
                6 -> findViewById<TextView>(R.id.textView6).text = ""
            }
            1 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView7).text = ""
                2 -> findViewById<TextView>(R.id.textView8).text = ""
                3 -> findViewById<TextView>(R.id.textView9).text = ""
                4 -> findViewById<TextView>(R.id.textView10).text = ""
                5 -> findViewById<TextView>(R.id.textView11).text = ""
                6 -> findViewById<TextView>(R.id.textView12).text = ""
            }
            2 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView13).text = ""
                2 -> findViewById<TextView>(R.id.textView14).text = ""
                3 -> findViewById<TextView>(R.id.textView15).text = ""
                4 -> findViewById<TextView>(R.id.textView16).text = ""
                5 -> findViewById<TextView>(R.id.textView17).text = ""
                6 -> findViewById<TextView>(R.id.textView18).text = ""
            }
            3 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView19).text = ""
                2 -> findViewById<TextView>(R.id.textView20).text = ""
                3 -> findViewById<TextView>(R.id.textView21).text = ""
                4 -> findViewById<TextView>(R.id.textView22).text = ""
                5 -> findViewById<TextView>(R.id.textView23).text = ""
                6 -> findViewById<TextView>(R.id.textView24).text = ""
            }
            4 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView25).text = ""
                2 -> findViewById<TextView>(R.id.textView26).text = ""
                3 -> findViewById<TextView>(R.id.textView27).text = ""
                4 -> findViewById<TextView>(R.id.textView28).text = ""
                5 -> findViewById<TextView>(R.id.textView29).text = ""
                6 -> findViewById<TextView>(R.id.textView30).text = ""
            }
            5 -> when (equation.size) {
                1 -> findViewById<TextView>(R.id.textView31).text = ""
                2 -> findViewById<TextView>(R.id.textView32).text = ""
                3 -> findViewById<TextView>(R.id.textView33).text = ""
                4 -> findViewById<TextView>(R.id.textView34).text = ""
                5 -> findViewById<TextView>(R.id.textView35).text = ""
                6 -> findViewById<TextView>(R.id.textView36).text = ""
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener { push("1"); update() }
        findViewById<Button>(R.id.button2).setOnClickListener { push("2"); update() }
        findViewById<Button>(R.id.button3).setOnClickListener { push("3"); update() }
        findViewById<Button>(R.id.button4).setOnClickListener { push("4"); update() }
        findViewById<Button>(R.id.button5).setOnClickListener { push("5"); update() }
        findViewById<Button>(R.id.button6).setOnClickListener { push("6"); update() }
        findViewById<Button>(R.id.button7).setOnClickListener { push("7"); update() }
        findViewById<Button>(R.id.button8).setOnClickListener { push("8"); update() }
        findViewById<Button>(R.id.button9).setOnClickListener { push("9"); update() }
        findViewById<Button>(R.id.button10).setOnClickListener { push("0"); update() }
        findViewById<Button>(R.id.button11).setOnClickListener { push("+"); update() }
        findViewById<Button>(R.id.button12).setOnClickListener { push("-"); update() }
        findViewById<Button>(R.id.button13).setOnClickListener { push("*"); update() }
        findViewById<Button>(R.id.button14).setOnClickListener { push("/"); update() }
        findViewById<Button>(R.id.button15).setOnClickListener { push("="); update() }
        findViewById<Button>(R.id.button16).setOnClickListener { judge() }
        findViewById<Button>(R.id.button17).setOnClickListener { popUpdate(); pop() }
    }
}