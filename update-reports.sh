#!/bin/sh

# Tama ei ole kovin tyylikas, mutta riittaa tahan

if [ -d tavla/target/site ]
  then
    rm -R doc/checkstyle-raportti/site
    cp -R tavla/target/site doc/checkstyle-raportti/
fi

viimeisinpit=$(ls -1 tavla/target/pit-reports/|tail -1)

if [ 'x'$viimeisinpit != 'x' -a -d tavla/target/pit-reports/$viimeisinpit ]
  then
    rm -R doc/pit-raportti/2016*
    cp -R tavla/target/pit-reports/$viimeisinpit doc/pit-raportti/

    sed "s#pit-raportti/2016[0-9]*/index.html#pit-raportti/$viimeisinpit/index.html#" <README.md >README.new

    rivituusi=$(wc -l <README.new)
    rivitvanha=$(wc -l <README.md)

    if [ $rivituusi = $rivitvanha ]
      then
        mv README.new README.md
      else 
        echo "README.md ja README.new ovat eri kokoiset"
        rm README.new
    fi
fi
