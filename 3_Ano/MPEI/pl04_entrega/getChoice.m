function op = getChoice()
    fprintf("\n");
    
    s1 = "1 - Your movies\n";
    s2 = "2 - Suggestion of movies based on other users\n";
    s3 = "3 - Suggestion of movies based on already evaluated movies\n";
    s4 = "4 - Movies feedback based on popularity\n";
    s5 = "5 - Exit\n";
    s6 = "Select Choice: ";
    
    prompt = strcat(s1, s2, s3, s4, s5, s6);
    op = input(prompt);
end