for graphe in `ls Sources | cut -d "." -f 1`
do
    dot -Gratio=auto -Tpng -o Images/$graphe.png Sources/$graphe.dot
done
