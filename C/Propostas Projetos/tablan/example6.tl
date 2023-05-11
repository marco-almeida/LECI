-- *NOT* minimum characteristics!

type table Music {
   track: integer;
   name: text;
};

type table Album {
   unique name: text; -- no repetition
   group: text;
   year: integer;
   tracks: Music;
};

type table Group {
   unique name: text; -- no repetition
   discography: Album;
};

group: Group;

group := new; -- empty Group with empty discography with empty tracks

read "albums.csv" using separator ";;", "Group" as name, "Album" as discography.name, "Group" as discography.group, "Year" as discography.year, "Track" as discography.tracks.track, "Name" as discography.tracks.name >> group;

group -> "All Groups:";
println group;
