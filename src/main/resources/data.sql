--
--INSERT INTO `alcohol` ( `aroma`, `content`, `finish`, `maincategory`, `name`, `nation`, `picture`, `subcategory`, `taste`) VALUES
--                                                                                                                               ('메이플, 꿀, 코코아, 구운 과일', '46', '모카, 술타니스, 다크 베리, 풍부한', '위스키', '벤리악 12년 700ml', '스코틀랜드', '/path/to/alcohol/storage/01', '싱글몰트', '체리, 구운 오렌지, 헤이즐넛, 건포도'),
--                                                                                                                               ( '과일, 꽃, 서양 배, 오크 ', '40', '달콤한, 부드러운, 스파이시', '위스키', '글렌피딕 12년 700ml', '스코틀랜드', '/path/to/alcohol/storage/02', '싱글몰트', '과일, 몰트, 버터스카치'),
--                                                                                                                               ( '강렬한, 바닐라, 사과, 배, 시트러스', '40', '스모키, 오크, 달콤한, 부드러운', '위스키', '조니워커 블랙 700ml', '스코틀랜드', '/path/to/alcohol/storage/03', '블렌디드', '스모키, 크리미, 바닐라, 달콤한, 말린 과일'),
--                                                                                                                               ( '건포도, 스모키, 샌달우드, 다크 초콜릿', '40', '스파이스, 블랙 페퍼, 스모키, 긴 여운', '위스키', '조니워커 블루 750ml', '스코틀랜드', '/path/to/alcohol/storage/04', '블렌디드', '헤이즐넛, 꿀, 장미 꽃, 셰리, 오렌지 '),
--                                                                                                                               ( '부드러운, 바닐라, 향긋한', '40', '오크, 견과류, 꿀', '위스키', '짐빔 화이트 750ml', '미국', '/path/to/alcohol/storage/05', '버번', '달콤한, 캐러멜, 조화로운'),
--                                                                                                                               ('라즈베리, 바닐라, 블랙베리, 오크', '13.5', '가죽, 부드러운, 체리, 스모키', '와인', '앙시앙 땅 까르베네 쉬라 750ml', '프랑스', '/path/to/alcohol/storage/06', '레드 와인', '딸기, 블랙베리, 블랙커런트, 자두'),
--                                                                                                                               ( '검붉은 과일, 바닐라, 오크, 흑연', '14.5', '블랙 체리, 블랙커런트, 삼나무', '와인', '서브미션 까베르네 소비뇽 750ml', '미국', '/path/to/alcohol/storage/07', '레드 와인', '체리, 바닐라, 초콜릿, 향신료'),
--                                                                                                                               ( '허브, 건초, 레몬, 시트러스', '12.5', '부드러운, 청량한, 상쾌한', '와인', '스톤 베이 소비뇽 블랑 750ml', '뉴질랜드', '/path/to/alcohol/storage/08', '화이트 와인', '풋사과, 풀, 산뜻한, 청포도'),
--                                                                                                                               ( '진한 장미, 나무, 야생화', '6.5', '산미, 세이지, 감귤', '와인', '보테가 모스카토 페탈로 750ml', '이탈리아', '/path/to/alcohol/storage/09', '스파클링 와인', '달콤한, 복숭아, 살구, 시트러스'),
--                                                                                                                               ( '달콤한, 베리, 미네랄리티', '12.5', '달콤한, 쌉쌀한, 실키', '와인', '도멘 데 디아블 로제 봉봉 750ml', '프랑스', '/path/to/alcohol/storage/10', '로제 와인', '부드러운, 산뜻한, 복합적인'),
--                                                                                                                               ('꿀, 멜론, 신선한, 향긋한 ', '20', '부드러운, 멜론, 새콤한', '리큐르', '미도리 750ml', '일본', '/path/to/alcohol/storage/11', '리큐르', '멜론, 시트러스, 달콤한'),
--                                                                                                                               ( '청사과, 달콤한, 바닐라, 가벼운, 오크', '35', '졸인 사과, 가벼운 스모키, 달콤한, 부드러운', '리큐르', '잭 다니엘스 애플 700ml', '미국', '/path/to/alcohol/storage/12', '리큐르', '꿀, 커스터드, 잘 익은 배, 오렌지 껍질'),
--                                                                                                                               ( '열대 과일, 코코넛', '21', '달콤한, 코코넛, 부드러운', '리큐르', '말리부 오리지널 700ml', '바베이도스', '/path/to/alcohol/storage/13', '리큐르', '달콤한, 코코넛, 버터, 바닐라'),
--                                                                                                                               ( '잼, 설탕에 절인 과일, 오크', '40', '바닐라, 복합적인, 따뜻한, 초콜릿', '브랜디', '헤네시 XO 700ml', '프랑스', '/path/to/alcohol/storage/14', '꼬냑', '스파이시, 다크 초콜릿, 후추'),
--                                                                                                                               ( '과일, 우아한, 오크, 헤이즐넛, 토스트, 브리오슈', '40', '긴 여운', '브랜디', '불라 깔바도스 VSOP 700ml', '프랑스', '/path/to/alcohol/storage/15', '깔바도스', '균형 잡힌, 바닐라, 오크, 원숙한, 사과'),
--                                                                                                                               ( '건자두, 다크 체리, 블랙베리, 레드베리,딸기', '40', '스파이시, 깔끔한', '브랜디', '자노 아르마냑 XO 700ml', '프랑스', '/path/to/alcohol/storage/16', '아르마냑', '우디, 구운 아몬드, 모과 잼, 절인 살구'),
--                                                                                                                               ( '강한 피트, 스모키, 부드러운, 달콤한, 홍차, 셰리, 바닐라', '43', '드라이한, 스파이시, 달콤한, 바닐라.', '위스키', '라가불린 16년 700ml', '스코틀랜드', '/path/to/alcohol/storage/17', '싱글몰트', '베이컨 , 해초, 말린 과일, 달콤한, 몰트, 따뜻한'),
--                                                                                                                               ( '감귤, 시트러스, 잘 익은 복숭아, 바닐라', '40', '오렌지, 복숭아, 깔끔한, 부드러운', '위스키', '글렌모렌지 오리지널 10년 700ml', '스코틀랜드', '/path/to/alcohol/storage/18', '싱글몰트', '과일, 꽃, 바닐라'),
--                                                                                                                               ( '몰트, 짭짤한, 달콤한', '46', '짭짤한, 스모키, 긴 여운', '위스키', '빅 피트 700ml', '스코틀랜드', '/path/to/alcohol/storage/19', '블렌디드', '피트, 달콤한, 스모키'),
--                                                                                                                               ( '신선한, 짭짤한, 바다 내음', '46.8', '긴 여운, 피트, 스모키', '위스키', '락 아일랜드 700ml', '스코틀랜드', '/path/to/alcohol/storage/20', '블렌디드', '꿀, 감초, 후추, 부드러운, 달콤한'),
--                                                                                                                               ( '바닐라, 캐러멜, 풍부한', '40', '그을린 오크, 바닐라, 꿀', '위스키', '위스키 로우 스트레이트 버번 750ml', '미국', '/path/to/alcohol/storage/21', '버번', '메이플 시럽, 달콤한, 부드러운'),
--                                                                                                                               ( '홍차, 오크, 바닐라, 캐러멜, 셰리에 절인 건포도', '46', '긴 여운, 부드러운', '위스키', '인드리 싱글몰트 위스키 700ml', '인도', '/path/to/alcohol/storage/22', '싱글몰트', '신선한, 우아한, 견과류, 꿀, 포도, 부드러운, 시트러스'),
--                                                                                                                               ( '스파이시, 초콜릿, 열대과일', '40', '스모키, 초콜릿, 로스팅한 커피', '위스키', '달모어 12년 700ml', '스코틀랜드', '/path/to/alcohol/storage/23', '싱글몰트', '바닐라, 셰리, 열대과일'),
--                                                                                                                               ( '오크, 꿀, 바닐라', '40', '은은한, 달콤한', '위스키', '발렌타인 마스터즈 700ml', '스코틀랜드', '/path/to/alcohol/storage/24', '블렌디드', '오렌지, 배, 감미로운'),
--                                                                                                                               ( '벌꿀, 오크, 바닐라', '40', '긴 여운, 달콤한, 산뜻한', '위스키', '발렌타인 12년 700ml', '스코틀랜드', '/path/to/alcohol/storage/25', '블렌디드', '벌꿀, 꽃, 오크'),
--                                                                                                                               ( '바닐라, 버터, 사과, 오렌지, 토피', '40', '달콤한, 따뜻한, 오크', '위스키', '맥캘란 12년 더블 캐스크 700ml', '스코틀랜드', '/path/to/alcohol/storage/26', '싱글몰트', '건포도, 시트러스, 감귤, 캐러멜'),
--                                                                                                                               ( '피트, 다크 초콜릿, 달콤한', '43', '부드러운', '위스키', '벤로막 15년 700ml', '스코틀랜드', '/path/to/alcohol/storage/27', '싱글몰트', '스모키, 스파이시, 셰리'),
--                                                                                                                               ( '건포도, 살구, 바나나, 피트', '48', '달콤한, 부드러운, 캐러멜, 긴 여운', '위스키', '라프로익 쿼터 캐스크 700ml', '스코틀랜드', '/path/to/alcohol/storage/28', '싱글몰트', '풍부한, 피트, 과일, 코코넛, 바닐라'),
--                                                                                                                               ( '스모키, 피트, 레몬', '56.2', '긴 여운, 바닐라, 피트, 스모키 ', '위스키', '아란 마크리무어 CS 700ml', '스코틀랜드', '/path/to/alcohol/storage/29', '싱글몰트', '몰트, 과일, 브리오슈, 구운 파인애플, 블랙 페퍼'),
--                                                                                                                               ( '감귤, 꽃, 바닐라, 서양 배, 체리', '40', '향긋한, 풍부한, 긴 여운, 오크', '위스키', '로얄살루트 21년 700ml', '스코틀랜드', '/path/to/alcohol/storage/30', '블렌디드', '마멀레이드, 멜론, 스모키, 헤이즐넛'),
--                                                                                                                               ( '과일, 셰리, 시트러스', '40', '균현 잡힌, 부드러운, 스파이시', '위스키', '더 페이머스 그라우스 700ml', '스코틀랜드', '/path/to/alcohol/storage/31', '블렌디드', '부드러운, 향신료, 말린 과일, 달콤한 '),
--                                                                                                                               ( '바닐라, 시트러스, 신선한', '40', '우아한, 부드러운, 상쾌한', '위스키', '커티 삭 700ml', '스코틀랜드', '/path/to/alcohol/storage/32', '블렌디드', '캐러멜, 몰트, 달콤한, 감귤'),
--                                                                                                                               ( '바닐라, 사과, 배, 시트러스', '40', '스모키, 오크, 달콤한, 부드러운', '위스키', '조니워커 블랙 1L', '스코틀랜드', '/path/to/alcohol/storage/03', '블렌디드', '크리미, 달콤한, 말린 과일, 스파이시'),
--                                                                                                                               ( '오크, 바닐라', '40', '부드러운', '위스키', '랭스 700ml', '스코틀랜드', '/path/to/alcohol/storage/34', '블렌디드', '달콤한, 부드러운'),
--                                                                                                                               ( '오크, 사과, 바닐라', '40', '부드러운, 가벼운, 깔끔한', '위스키', '미스터 보스턴 버번 1L', '미국', '/path/to/alcohol/storage/35', '버번', '몰트, 바닐라, 오렌지'),
--                                                                                                                               ('바닐라, 오렌지, 오크, 캐러멜', '50.5', '다크 초콜릿, 오크', '위스키', '와일드 터키 101 18년 700ml', '미국', '/path/to/alcohol/storage/36', '버번', '계피, 과일, 스파이스, 호밀'),
--                                                                                                                               ( '바닐라, 캐러멜, 오크, 과일', '45', '향신료, 부드러운, 깔끔한', '위스키', '메이커스 마크 750ml ', '미국', '/path/to/alcohol/storage/37', '버번', '달콤한, 균형 잡힌, 프루티'),
--                                                                                                                               ( '민트, 바닐라, 균형 잡힌', '45', '긴 여운, 바닐라, 우아한', '위스키', '버팔로 트레이스 750ml', '미국', '/path/to/alcohol/storage/38', '버번', '오크, 토피, 향신료, 호밀'),
--                                                                                                                               ( '견과류, 몰트, 과일', '50', '풍부한, 긴 여운, 부드러운', '위스키', '납 크릭 750ml', '미국', '/path/to/alcohol/storage/39', '버번', '오크, 달콤한, 스파이시'),
--                                                                                                                               ( '토피, 오렌지 껍질, 허브, 꿀, 가죽', '45', '긴 여운, 드라이한, 바닐라, 레드 커런트', '위스키', '이글 레어 10년 750ml', '미국', '/path/to/alcohol/storage/40', '버번', '오크, 설탕, 코팅된 아몬드, 코코아'),
--                                                                                                                               ( '바닐라, 스파이스, 시나몬, 캐러멜, 풀', '57.15', '오크, 긴 내음', '위스키', '노아스 밀 750ml', '미국', '/path/to/alcohol/storage/41', '버번', '민트, 바닐라, 시나몬, 옥수수, 풀내음'),
--                                                                                                                               ( '시나몬, 민트, 정향', '33', '시나몬, 강렬한', '리큐르', '파이어볼 500ml', '캐나다', '/path/to/alcohol/storage/42', '리큐르', '달콤한, 시나몬, 오렌지, 소나무'),
--                                                                                                                               ( '오렌지 껍질, 바닐라, 허브', '11', '부드러운, 긴 여운, 향긋한', '리큐르', '아페롤 700ml', '이탈리아', '/path/to/alcohol/storage/43', '리큐르', '달콤한, 쌉쌀한, 오렌지, 허브'),
--                                                                                                                               ( '시트러스, 감귤, 오렌지', '40', '달콤한', '리큐르', '디카이퍼 트리플 섹 700ml', '네덜란드', '/path/to/alcohol/storage/44', '리큐르', '오렌지'),
--                                                                                                                               ( '감귤, 시트러스, 오렌지', '21', '달콤한, 오렌지', '리큐르', '볼스 블루 큐라소 700ml', '네덜란드', '/path/to/alcohol/storage/45', '리큐르', '달콤한, 쌉쌀한'),
--                                                                                                                               ( '상큼한, 달콤한, 유자', '8', '산뜻한, 깔끔한, 상쾌한', '리큐르', '유즈 28 720ml', '일본', '/path/to/alcohol/storage/46', '리큐르', '새콤한, 벌꿀, 시트러스'),
--                                                                                                                               ( '달콤한, 라임', '18', '상큼한, 쌉쌀한', '리큐르', '바카디 클래식 칵테일 모히토 700ml', '이탈리아', '/path/to/alcohol/storage/47', '리큐르', '라임, 모히또'),
--                                                                                                                               ('땅콩 버터, 버번, 다크 초콜릿', '30', '스모키, 부드러운, 메이플 시럽', '리큐르', '벨로틴 피넛 버터 초콜릿 리큐르 750ml', '미국', '/path/to/alcohol/storage/48', '리큐르', '바닐라, 견과류, 고소한'),
--                                                                                                                               ('상큼한, 자몽, 시트러스', '17', '달콤한, 쌉쌀한', '리큐르', '볼스 핑크 자몽 피크닉 패키지 700ml', '네덜란드', '/path/to/alcohol/storage/49', '리큐르', '자몽, 달콤한, 쌉쌀한'),
--                                                                                                                               ('아몬드, 고소한, 강렬한', '23', '부드러운, 달콤한, 쌉쌀한', '리큐르', '마리브리자드 아마레또 700ml', '프랑스', '/path/to/alcohol/storage/50', '리큐르', '아몬드, 달콤한, 체리');
--
--
--INSERT INTO `favorites` ( `email`, `name`) VALUES
--                                               ('aaa@naver.com', '벤리악 12년 700ml'),
--                                               ( 'aaa@naver.com', '짐빔 화이트 750ml'),
--                                               ( 'ddd@naver.com', '짐빔 화이트 750ml');
--
--INSERT INTO `purchase` (`price`, `purchaseday`, `address`, `email`, `marketname`, `name`, `order_number`, `division`,  `ordertype`, `amount`) VALUES
--                                                                                                                                                  (45000, '2024-04-20',  '대구', 'aaa@naver.com', '대구', '발렌타인 마스터즈 700ml', '1', 'BUY',  'PICKUP',5),
--                                                                                                                                                  (45000, '2024-04-20',  '대구', 'aaa@naver.com', '대구', '빅 피트 700ml', '2', 'BUY',  'DELIVERY',6),
--                                                                                                                                                  (45000, '2024-04-21',  '대구', 'aaa@naver.com', '대구', '헤네시 XO 700ml', '3', 'BUY',  'DELIVERY',7),
--                                                                                                                                                  (45000, '2024-04-21',  '대구', 'aaa@naver.com', '대구', '헤네시 XO 700ml', '4', 'RETURN',  'DELIVERY',6),
--                                                                                                                                                  (45000, '2024-04-21',  '대구', 'aaa@naver.com', '대구', '달모어 12년 700ml', '5', 'BUY',  'PICKUP',5),
--                                                                                                                                                  (45000, '2024-04-20',  '대구', 'aba@naver.com', '대구', '발렌타인 마스터즈 700ml', '1', 'RETURN',  'PICKUP',4),
--                                                                                                                                                  (55000, '2024-04-21',  '대구', 'aaa@naver.com', '대구', '달모어 12년 700ml', '5', 'RETURN', 'DELIVERY',5),
--                                                                                                                                                  (55000, '2024-04-21',  '대구', 'aba@naver.com', '대구', '달모어 12년 700ml', '5', 'RETURN', 'DELIVERY',6),
--                                                                                                                                                  (55000, '2024-04-21',  '대구', 'aba@naver.com', '대구', '달모어 12년 700ml', '5', 'RETURN', 'DELIVERY',6),
--                                                                                                                                                  (55000, '2024-04-21',  '대구', 'aba@naver.com', '대구', '발렌타인 마스터즈 700ml', '5', 'RETURN',  'DELIVERY',7);