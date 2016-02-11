package com.example.wlq.calculatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_0;//0数字的按钮
    private Button btn_1;//1数字的按钮
    private Button btn_2;//2数字的按钮
    private Button btn_3;//3数字的按钮
    private Button btn_4;//4数字的按钮
    private Button btn_5;//5数字的按钮
    private Button btn_6;//6数字的按钮
    private Button btn_7;//7数字的按钮
    private Button btn_8;//8数字的按钮
    private Button btn_9;//9数字的按钮
    private Button btn_point;//小数点的按钮
    private Button btn_clear;//清除的按钮
    private Button btn_del;//删除的按钮
    private Button btn_divide;//除法的按钮
    private Button btn_plus;//加号的按钮
    private Button btn_multiply;//乘号的按钮
    private Button btn_minus;//减号的按钮
    private Button btn_equal;//等号的按钮
    private EditText et_input;//显示计算结果
    boolean clear_flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button)findViewById(R.id.bt_0);
        btn_1 = (Button)findViewById(R.id.bt_1);
        btn_2 = (Button)findViewById(R.id.bt_2);
        btn_3 = (Button)findViewById(R.id.bt_3);
        btn_4 = (Button)findViewById(R.id.bt_4);
        btn_5 = (Button)findViewById(R.id.bt_5);
        btn_6 = (Button)findViewById(R.id.bt_6);
        btn_7 = (Button)findViewById(R.id.bt_7);
        btn_8 = (Button)findViewById(R.id.bt_8);
        btn_9 = (Button)findViewById(R.id.bt_9);
        btn_point = (Button)findViewById(R.id.bt_point);
        btn_clear = (Button)findViewById(R.id.bt_clear);
        btn_del = (Button)findViewById(R.id.bt_del);
        btn_divide = (Button)findViewById(R.id.bt_divide);
        btn_multiply = (Button)findViewById(R.id.bt_multiply);
        btn_minus = (Button)findViewById(R.id.bt_minus);
        btn_plus = (Button)findViewById(R.id.bt_plus);
        btn_equal = (Button)findViewById(R.id.bt_equal);
        et_input = (EditText)findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()){
            case R.id.bt_0:
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_point:
                if (clear_flag){
                    clear_flag=false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.bt_divide:
            case R.id.bt_multiply:
            case R.id.bt_minus:
            case R.id.bt_plus:
                if (clear_flag){
                    clear_flag=false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.bt_clear:
                clear_flag=false;
                et_input.setText("");
                break;
            case R.id.bt_del:
                if (clear_flag){
                    clear_flag=false;
                    str = "";
                    et_input.setText("");
                }
                if (str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }

                break;
            case R.id.bt_equal:
                getResult();
                break;

            default:
                break;
        }

    }
    /*
    *单独计算运算结果
     */
    private void  getResult(){
        String exp = et_input.getText().toString();
        if (exp==null||exp.equals("")){
            return;
        }
        if (!exp.contains(" ")){
            return;
        }
        if (clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag = true;
        double result = 0;
        String s1 = exp.substring(0,exp.indexOf(" "));//运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//运算符
        String s2 = exp.substring(exp.indexOf(" ") + 2, exp.length());//运算符后面的字符
        if (!s1.equals("")&&!s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);

            if (op.equals("＋")) {
                result = d1 + d2;

            } else if (op.equals("－")) {
                result = d1 - d2;

            } else if (op.equals("×")) {
                result = d1 * d2;

            } else if (op.equals("÷")) {
                if (d2 == 0) {
                    result = 0;
                } else {
                    result = d1 / d2;
                }

            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")){
               int r = (int)result;
                et_input.setText(r+"");
            }else {
                et_input.setText(result+"");
            }
        } else if (!s1.equals(" ")&&s2.equals(" ")){
            et_input.setText(exp);

        } else if (s1.equals(" ")&&!s2.equals(" ")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("＋")) {
                result = 0 + d2;

            } else if (op.equals("－")) {
                result = 0 - d2;

            } else if (op.equals("×")) {
                result = 0;

            } else if (op.equals("÷")) {
                result = 0;

            }
            if (!s2.contains(".")){
                int r = (int)result;
                et_input.setText(r+"");

            }else{
                et_input.setText(result+"");
            }

        }else{
            et_input.setText("");
        }

    }
}
