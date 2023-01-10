function userID = getID()
    prompt = 'Enter a number between 1 and 943: ';
    userID = input(prompt);

    while floor(userID) ~= userID || userID < 1 || userID > 943
        fprintf('Invalid input. Please try again.\n');
        userID = input(prompt);
    end

end
