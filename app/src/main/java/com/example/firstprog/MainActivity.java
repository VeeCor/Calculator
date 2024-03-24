package com.example.firstprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    TextView LastHistory, Insertion_area;
    Button button_ac, button_back, button_div,
            button1, button2, button3, button_multiplication,
            button4, button5, button6, button_minus,
            button7, button8, button9, button_plus,
            button0, button_dot, button_equals;
    String symbol, symbol2, num1, num2;
    Double result;
    Boolean EqualsUsed = false, DivZero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Прикрепление id объектов к переменным
        LastHistory = findViewById(R.id.LastHistory);
        Insertion_area = findViewById(R.id.Insertion_area);
        button_ac = findViewById(R.id.button_ac);
        button_back = findViewById(R.id.button_back);
        button_div = findViewById(R.id.button_div);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button_multiplication = findViewById(R.id.button_multiplication);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button_minus = findViewById(R.id.button_minus);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button_plus = findViewById(R.id.button_plus);
        button0 = findViewById(R.id.button0);
        button_dot = findViewById(R.id.button_dot);
        button_equals = findViewById(R.id.button_equals);

        //Вызов метода OnClickListener
        button1.setOnClickListener(ClickNumberButton);
        button2.setOnClickListener(ClickNumberButton);
        button3.setOnClickListener(ClickNumberButton);
        button4.setOnClickListener(ClickNumberButton);
        button5.setOnClickListener(ClickNumberButton);
        button6.setOnClickListener(ClickNumberButton);
        button7.setOnClickListener(ClickNumberButton);
        button8.setOnClickListener(ClickNumberButton);
        button9.setOnClickListener(ClickNumberButton);
        button0.setOnClickListener(ClickNumberButton);
        button_dot.setOnClickListener(ClickNumberButton);
        button_plus.setOnClickListener(ClickSymbolButton);
        button_minus.setOnClickListener(ClickSymbolButton);
        button_div.setOnClickListener(ClickSymbolButton);
        button_multiplication.setOnClickListener(ClickSymbolButton);
        button_back.setOnClickListener(ClickBackButton);
        button_ac.setOnClickListener(ClickClearButton);
        button_equals.setOnClickListener(ClickEqualButton);

        buttonEffect(button0);
        buttonEffect(button1);
        buttonEffect(button2);
        buttonEffect(button3);
        buttonEffect(button4);
        buttonEffect(button5);
        buttonEffect(button6);
        buttonEffect(button7);
        buttonEffect(button8);
        buttonEffect(button9);
        buttonEffect(button_ac);
        buttonEffect(button_back);
        buttonEffect(button_dot);
        buttonEffect(button_equals);
    }

    private final View.OnClickListener ClickBackButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!EqualsUsed) {
                //Создание переменной text и сохранение в нее введенного в область ввода числа
                String text = Insertion_area.getText().toString();
                /*
                Удаление последнего символа из области ввода, при условии, что в области ввода находится больше одного символа;
                Вывод в область ввода число 0, при условии, что в области ввода не более одного символа;
                 */
                if (text.length() > 1) {
                    String newText = text.substring(0, text.length() - 1);
                    Insertion_area.setText(newText);
                } else {
                    Insertion_area.setText("0");
                }
            }
        }
    };

    private final View.OnClickListener ClickClearButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            Вывод в область ввода числа 0, как значения по умолчанию;
            Удаление значений из объекта LastHistory, а также из переменных num1 и num2;
            Сброс значения переменной EqualsUsed, отвечающей за использование знака равно;
             */
            Insertion_area.setText("0");
            LastHistory.setText(null);//
            num1 = null;
            num2 = null;
            EqualsUsed = false;
        }
    };

    private final View.OnClickListener ClickEqualButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(num1 != null) {
                //Вызов метода смены цвета кнопок символов
                changeColorSymbolButton();
                //Сохранение введенного в области ввода числа, при условии, что знак равно не был использован ранее
                if (!EqualsUsed) {
                    num2 = Insertion_area.getText().toString();
                }
            /*
            Преобразование введенных чисел в переменных num1 и num2 из формата string в формат double;
            Создание переменной LastHistoryText для сохранения получившегося примера в привычном математическом виде с отображением чисел и знака;
            Запись в область вывода истории вычисляемого примера из переменной LastHistoryText;
            Отчистка области ввода;
             */
                double nam1_double = Double.parseDouble(num1);
                double nam2_double = Double.parseDouble(num2);
                String LastHistoryText = num1 + " " + symbol + " " + num2;
                LastHistory.setText(LastHistoryText);
                Insertion_area.setText(null);
            /*
            Проверка "какой знак был использован";
            Проведение вычислений в зависимости от знака;
             */
                switch (symbol) {
                    case "+":
                        result = nam1_double + nam2_double;
                        break;
                    case "-":
                        result = nam1_double - nam2_double;
                        break;
                    case "×":
                        result = nam1_double * nam2_double;
                        break;
                    case "÷":
                        //Проверка деления на 0
                        if (nam2_double != 0f) {
                            result = nam1_double / nam2_double;
                        } else {
                            DivZero = true;
                        }
                        break;
                }
                String resultFormat = Double.toString(result);
                if (DivZero) {
                    //Вывод ошибки если была потытка деления на 0
                    String Error = "Error";
                    Insertion_area.setText(Error);
                    DivZero = false;
                } else if (result % 1 == 0) {
                    //Отображение целого числа формата double без точки и нуля на конце и вывод результата
                    resultFormat = resultFormat.replaceAll("0*$", "");
                    resultFormat = resultFormat.replaceAll("\\.$", "");
                    Insertion_area.setText(resultFormat);
                } else {
                    //Вывод результата
                    if (resultFormat.length() > 10) {
                        resultFormat = resultFormat.substring(0, 11);
                        while(resultFormat.charAt(resultFormat.length() - 1) == '0'){
                            resultFormat = resultFormat.substring(0, resultFormat.length() - 1);
                        }
                        if(resultFormat.length() > 10){
                            BigDecimal resultBigDecimal = new BigDecimal(resultFormat);
                            int dotIndex = resultFormat.indexOf('.');
                            int ScaleNum;
                            switch (dotIndex){
                                case 1:
                                    ScaleNum = 8;
                                    break;
                                case 2:
                                    ScaleNum = 7;
                                    break;
                                case 3:
                                    ScaleNum = 6;
                                    break;
                                case 4:
                                    ScaleNum = 5;
                                    break;
                                case 5:
                                    ScaleNum = 4;
                                    break;
                                case 6:
                                    ScaleNum = 3;
                                    break;
                                case 7:
                                    ScaleNum = 2;
                                    break;
                                default:
                                    ScaleNum = 1;
                                    break;
                            }
                            BigDecimal roundedResult = resultBigDecimal.setScale(ScaleNum, RoundingMode.HALF_UP);
                            resultFormat = roundedResult.toString();
                            Insertion_area.setText(resultFormat);
                        }else{
                            Insertion_area.setText(resultFormat);
                        }
                    } else {
                        Insertion_area.setText(resultFormat);
                    }
                }
            /*
            Присвоение переменной числа из области ввода;
            Удаление знака из переменной symbol2;
            Смена значения на true, означающее, что знак равно был нажат;
             */
                num1 = Insertion_area.getText().toString();
                symbol2 = null;
                EqualsUsed = true;
            }
        }
    };

    private final View.OnClickListener ClickNumberButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            Вызов метода удаления всего, если вычисления были выполнены (знак равно нажимался)
            и при этом не выбран знак для дальнейших вычислений
            */
            if(EqualsUsed && symbol2 == null) {
                ClickClearButton.onClick(v);
            }
            /*
            Ввод первого числа в поле истории и отчистка поля ввода
            при условии, что первое число введено и при этом поле истории пустое,
            или, если это не первое вычислительное действие (знак равно уже был нажат ранее);
            Сброс события "знак равно был нажат";
            */
            if ((num1 != null && LastHistory.getText().toString().isEmpty()) | EqualsUsed) {
                LastHistory.setText(num1);
                Insertion_area.setText(null);
                if (EqualsUsed){
                    EqualsUsed = false;
                }
            }
            //Преобразование объекта view в button и считываение текста кнопки
            Button btn = (Button) v;
            String btnText = btn.getText().toString();
            /*
            Установка ограничения на ввод - не более 6 символов;
            Вывод числа в область ввода, если в области ввода введен 0;
            Добавление цифры с кнопки в конец вводимого числа, если в области ввода числа отличные от 0;
            */
            if(Insertion_area.length() < 6) {
                String text = Insertion_area.getText().toString();
                if (Insertion_area.getText().toString().equals("0")) {
                    if(btnText.equals(".")){
                        btnText = "0.";
                        Insertion_area.setText(btnText);
                    }else {
                        Insertion_area.setText(btnText);
                    }
                } else if (!text.contains(".")){
                    Insertion_area.append(btnText);
                } else{
                    if(!btnText.equals(".")){
                        Insertion_area.append(btnText);
                    }
                }
            }
        }
    };

    private final View.OnClickListener ClickSymbolButton = new View.OnClickListener() {//Метод, описывающий действия при нажатии кнопки с символом
        @Override
        public void onClick(View v) {
            /*
            Вызов метода для смены цвета кнопки;
            Сохранение другого цвета для нажатой кнопки в переменную;
            Преобразование объекта view в button и изменение цвета кнопки;
            Сохранение нажатого знака в переменную symbol;
            */
            changeColorSymbolButton();
            int color = ContextCompat.getColor(MainActivity.this, R.color.button_dark_red);
            Button btn = (Button) v;
            btn.setBackgroundTintList(ColorStateList.valueOf(color));
            symbol = btn.getText().toString();
            /*
            Присвоение переменной введенного числа, если вычисление проводится первый раз (знак равно еще ни разу не был нажат);
            Присвоение нового символа переменной symbol2 при продолжении вычисления (знак равно уже был нажат);
             */
            if(!EqualsUsed) {
                num1 = Insertion_area.getText().toString();
            }else {
                symbol2 = btn.getText().toString();
            }
        }
    };

    private void changeColorSymbolButton (){
        //Присвоение переменной основного красного цвета кнопок
        int currentColor = ContextCompat.getColor(MainActivity.this, R.color.button_red);

        //Изменение цвета кнопки на основной красный
        button_plus.setBackgroundTintList(ColorStateList.valueOf(currentColor));
        button_minus.setBackgroundTintList(ColorStateList.valueOf(currentColor));
        button_div.setBackgroundTintList(ColorStateList.valueOf(currentColor));
        button_multiplication.setBackgroundTintList(ColorStateList.valueOf(currentColor));
    }

    private void buttonEffect(@NonNull View button) {
        button.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    int ClickColor = ContextCompat.getColor(MainActivity.this, R.color.button_dark_red);
                    v.setBackgroundTintList(ColorStateList.valueOf(ClickColor));
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    int currentColor = ContextCompat.getColor(MainActivity.this, R.color.button_red);
                    v.setBackgroundTintList(ColorStateList.valueOf(currentColor));
                    v.performClick();
                    return true;
                }
            }
            return false;
        });
    }

//    private void showinfo (String text){
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }
}