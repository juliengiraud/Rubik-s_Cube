for file in `ls graph_data/`
do
    name=`echo $file | cut -d "." -f 1`
    
    echo { > JSON_data/$name.json
    echo "   " \"rotation\": [ >> JSON_data/$name.json
    for line in `cat graph_data/$file`
    do 
        input=`echo $line | cut -d ";" -f 1`
        output=`echo $line | cut -d ";" -f 2`
        move=`echo $line | cut -d ";" -f 3`
        echo "       " { >> JSON_data/$name.json
        echo "           " \"input\": $input, >> JSON_data/$name.json
        echo "           " \"move\": \"$move\", >> JSON_data/$name.json
        echo "           " \"output\": $output >> JSON_data/$name.json
        echo "        "}, >> JSON_data/$name.json
    done
    echo "    " ] >> JSON_data/$name.json
    echo } >> JSON_data/$name.json    
done
