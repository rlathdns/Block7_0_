package com.cookandroid.block7
//Event_independence_fightSounEunju: 은주 소운 싸움(소운다치지않았음)
class Event_independence_fightSounEunju(GameActivity: GameActivity, eventName: String,  type: Int, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, type, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_independence_fightSounEunju_pre"))
        setPostScript(getScript("event_independence_fightSounEunju_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        if(GameActivity.member_soun.getIsIn() && GameActivity.member_eunju.getIsIn()){
            isAvailable = true
        }
        else{
            isAvailable = false
        }
    }

    // 이벤트 효과 메소드
    override fun eventEffect(choose_value: Boolean) {
        if(choose_value) { // 소운이를 고를경우
            val randomValue = Math.random()
            // 50%의 확률로 소운이가다칩니다.
            if (randomValue < 0.5) {
                if(!GameActivity.member_soun.getStateIsHurt()){
                    GameActivity.member_soun.stateChangeHurt()
                    addPostScript("소운은 은주의 날라차기를 피하지 못했다.")
                }
            }
        } else { // 은주를 고를경우
            val randomValue = Math.random()
            setPostScript(getScript("event_independence_fightSounEunju_tmp"))
            if(randomValue < 0.5){
                loseRandomItem()
            }
            else{
                loseRandomItem()
                addPostScript("은주는 그 와중에 물건을 부쉈다")
            }
        }
    }
}