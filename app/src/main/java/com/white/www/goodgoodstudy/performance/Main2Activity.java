package com.white.www.goodgoodstudy.performance;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.white.www.goodgoodstudy.R;

//属性动画
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button button = findViewById(R.id.btn);

        final int[] position = {0};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                //view动画是个假象  帧动画和补间动画
//                Animation animation= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.translate);
//                button.startAnimation(animation);

                //设置它的坐标x为 200
//                position[0] +=100;
//                v.setTranslationX(position[0]);
//                v.setAlpha((float) Math.random());

                //1.简单的属性动画
                //设置平移，从x坐标为0f 移动到x坐标为300f
//                ObjectAnimator oa1 = ObjectAnimator.ofFloat(v,"translationX",0f,300f);
//                oa1.setDuration(200);
//                oa1.start();
//
//                ObjectAnimator oa2=ObjectAnimator.ofFloat(v,"translationY",0f,300f);
//                oa2.setDuration(200);
//                oa2.start();

                //绕着自身X水平线 旋转
//                ObjectAnimator oa3=ObjectAnimator.ofFloat(v,"rotationX",0f,300f);
//                oa3.setDuration(500);
//                oa3.start();


                //2.多个动画同时执行 -----------------
                // 办法1：设置动画监听，开始第一个动画同时开启其他动画
                ObjectAnimator oa4 = ObjectAnimator.ofFloat(v, "", 0f, 200f); //没有属性的时候，就是valueAnimator
                oa4.setDuration(500);
                //设置动画监听
                oa4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        //动画在执行的过程当中，不断的调用此方法
                        float animatedFraction = animation.getAnimatedFraction();//动画执行的百分比
                        //得到duration时间内values当中的某一个中间值。 0f~200f
                        float animatedValue = (float) animation.getAnimatedValue();//
                        button.setAlpha((float) (animatedValue / 200 + 0.5));//0-1
                        button.setScaleX((float) (animatedValue / 200 + 0.5));//0-1
                        button.setScaleY((float) (animatedValue / 200 + 0.5));//0-1
                    }
                });

//                oa4.start();
                oa4.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        button.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        button.setEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }
                });


                // 方法2:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 200f);
                animator.setDuration(500);
                //设置动画监听
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        //动画在执行的过程当中，不断的调用此方法
                        float animatedFraction = animation.getAnimatedFraction();//动画执行的百分比
                        //得到duration时间内values当中的某一个中间值。 0f~200f
                        float animatedValue = (float) animation.getAnimatedValue();//
                        button.setAlpha((float) (animatedValue / 200 + 0.5));//0-1
                        button.setScaleX((float) (animatedValue / 200 + 0.5));//0-1
                        button.setScaleY((float) (animatedValue / 200 + 0.5));//0-1
                    }
                });
//                animator.start();

                //方法3:
                PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("alpha", 1f, 0.7f, 1f);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.7f, 1f);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.7f, 1f);
//                PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("translationX",0f,300f);

                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(button, holder, holder2, holder3);
                objectAnimator.setDuration(200);
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        animation.getAnimatedValue();
                        long currentPlayTime = animation.getCurrentPlayTime();
                        System.out.println("animatedValue:" + animatedValue + "," + "currentPlayTime:" + currentPlayTime);
                    }
                });
//                objectAnimator.start();


                //方法4
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(button, "alpha", 1f, 0.7f, 1f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0.7f, 1f);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0.7f, 1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(500);
//                animatorSet.playTogether(animator1, animator2, animator3);
                animatorSet.play(animator1);//单独执行某个动画
//                animatorSet.playSequentially(animator1, animator2, animator3); //依次执行
                animator.start();

            }
        });


    }


}
