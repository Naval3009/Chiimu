package com.example.lottieanimation_splashscreen

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chiimu_minor_project_version_1.MainActivity
import com.example.chiimu_minor_project_version_1.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController, context: MainActivity)
{
    val animations = listOf(
        R.raw.study,
        R.raw.hustle,
        R.raw.group
    )
    val title= listOf(
        "Explore the Skies",
        "Escape the old-ways to make team",
        "let's bring a Change ",
    )
     val description= listOf(
         "No issues if your are noob we got your back..",
         "Want your idea to become reality hop in....",
         "Let's build a team on which you can rely 共同",
     )
    val pagerState = rememberPagerState{animations.size}

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        HorizontalPager(state = pagerState, Modifier.wrapContentSize()
        ){ currentPage->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))
                LottieAnimation(
                    composition = composition,
                    iterations =  LottieConstants.IterateForever,
                    modifier= Modifier.size(300.dp)
                )
                Text(
                    text= title[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text= description[currentPage],
                    Modifier.padding(45.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

            }//column


        }//horizontal pager

        PageIndicator(//making custom indicator
            pageCount=animations.size,
            currentPage=pagerState.currentPage,
            modifier=Modifier.padding(60.dp)
        )

    }//main column holding the pager
    ButtonsSection(
        pagerState = pagerState,
        navController = navController,
        context = context
    )


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonsSection(pagerState: PagerState, navController: NavHostController, context: MainActivity) {
    val scope= rememberCoroutineScope()

    Box(modifier= Modifier
        .fillMaxSize()
        .padding(30.dp))
    {
        if(pagerState.currentPage!=2)
        {
            Text(text="NEXT",//implementing the logic for the next button
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color= Color.Black,
                modifier= Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                       scope.launch {
                           val nextPage= pagerState.currentPage+1//like here +1 is done in the pagerState.currentPage
                           pagerState.scrollToPage(nextPage)

                       }

                    }
            )

            Text(text="BACK",//implementing the logic for the back button
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color= Color.Black,
                modifier= Modifier
                    .align(Alignment.BottomStart)
                    .clickable {
                        scope.launch {
                            val prevPage= pagerState.currentPage-1//like here -1 is done in the pagerState.currentPage
                            if(prevPage>=0){// page will be 0, 1, 2 like splash
                                pagerState.scrollToPage(prevPage)
                            }
                        }

                    },
                )
        }else{
            OutlinedButton(onClick = {
                onBoardingIsFinished(context=context)
                navController.popBackStack()//removing the back stack so that by pressing back it will not jump to the previous pages
                navController.navigate("Home")/////////////////////////////////////////////////////////////

            },modifier= Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Color(0x52FD5F5F)
                    )
            ) {
                Text(
                    text = "GET STARTED WITH US",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                
            }
            
        }
    }

}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount)
        {
            IndicatorSingleDot(isSelected= it==currentPage)

        }

    }

}

@Composable//here we are making the customise page indicator as we move via the help of pager
fun IndicatorSingleDot(isSelected: Boolean) {
    val widthOfSingleDot= animateDpAsState(targetValue = if(isSelected) 35.dp else 15.dp,label ="")
    Box(modifier= Modifier
        .padding(2.dp)
        .height(15.dp)
        .width(widthOfSingleDot.value)
        .clip(CircleShape)
        .background(
            if (isSelected) Color(0xFFE92F1E) else Color(0x52FD5F5F)
        )
    )
}

private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)// In this function the name "onboarding" should be same in both be it splash be it onBOarding screeen
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}



