INSERT INTO users (displayName, city, state, zipCode, peanutAllergyInterest, eggAllergyInterest, dairyAllergyInterest) VALUES ('David', 'Tauranga', 'Bay of Plenty', '111', FALSE, FALSE, FALSE);
INSERT INTO users (displayName, city, state, zipCode, peanutAllergyInterest, eggAllergyInterest, dairyAllergyInterest) VALUES ('Ron', 'Seoul', 'Seoul', '222', FALSE, TRUE, FALSE);
INSERT INTO restaurant (name, city, state, zipCode) VALUES ('David Restaurant', 'Tauranga', 'Bay of Plenty', '111');
INSERT INTO restaurant (name, city, state, zipCode) VALUES ('Ron Restaurant', 'Seoul', 'Seoul', '222');
INSERT INTO diningreview (displayName, zipCode, restaurantId, peanutScore) VALUES ('David', '111', 1, 4);
INSERT INTO diningreview (displayName, zipCode, restaurantId, eggScore) VALUES ('David', '222', 1, 4);
