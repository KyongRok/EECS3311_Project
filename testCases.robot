*** Settings ***
Library           Collections
Library           RequestsLibrary
Test Timeout      30 seconds

*** Test Cases ***
addActorPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    name=Tom Holland  actorId=4000                         
    ${resp}=    Put Request    localhost    /api/v1/addActor    data=${params}    headers=${headers}
    ${params}=    Create Dictionary    name=Zendaya  actorId=4100                         
    ${resp}=    Put Request    localhost    /api/v1/addActor    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

addActorFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    name=Tom Holland
    ${resp}=    Put Request    localhost    /api/v1/addActor    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400
    
addMoviePass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    name=Spider-Man  movieId=1000
    ${resp}=    Put Request    localhost    /api/v1/addMovie    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

addMovieFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    name=Spider-Man  
    ${resp}=    Put Request    localhost    /api/v1/addMovie    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400

addRelationshipPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  movieId=1000
    ${resp}=    Put Request    localhost    /api/v1/addRelation    data=${params}    headers=${headers}
    ${params}=    Create Dictionary    actorId=nm0000102  movieId=1000
    ${resp}=    Put Request    localhost    /api/v1/addRelation    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

addRelationshipFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  
    ${resp}=    Put Request    localhost    /api/v1/addRelation    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400

getActorPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  
    ${resp}=    Get Request    localhost    /api/v1/getActor    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

getActorFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4242
    ${resp}=    Get Request    localhost    /api/v1/getActor    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

getMoviePass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    movieId=1000
    ${resp}=    Get Request    localhost    /api/v1/getMovie    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

getMovieFail2
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    movieId=1001
    ${resp}=    Get Request    localhost    /api/v1/getMovie    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

hasRelationshipPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=nm0000102     movieId=1000
    ${resp}=    Get Request    localhost    /api/v1/hasRelation    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

hasRelationshipFail2
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4800     movieId=1001
    ${resp}=    Get Request    localhost    /api/v1/hasRelation    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

computeBaconNumberPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000    
    ${resp}=    Get Request    localhost    /api/v1/computeBaconNumber    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

computeBaconNumberFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary       
    ${resp}=    Get Request    localhost    /api/v1/computeBaconNumber    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400

computeBaconPathPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000    
    ${resp}=    Get Request    localhost    /api/v1/computeBaconNumber    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

computeBaconPathFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary       
    ${resp}=    Get Request    localhost    /api/v1/computeBaconPath    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400

wonOscarPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  numberOfOscars=1
    ${resp}=    Put Request    localhost    /api/v1/wonOscar    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

wonOscarFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=2301  numberOfOscars=1
    ${resp}=    Put Request    localhost    /api/v1/wonOscar    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

displayOscarPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  
    ${resp}=    Get Request    localhost    /api/v1/displayOscar    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

displayOscarFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=2401  
    ${resp}=    Get Request    localhost    /api/v1/displayOscar    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

addPersonalRelationPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId1=4000  actorId2=4100
    ${resp}=    Put Request    localhost    /api/v1/addPersonalRelation    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

addPersonalRelationFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId1=4000  actorId2=200
    ${resp}=    Put Request    localhost    /api/v1/addPersonalRelation    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

getPersonalRelationPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=4000  
    ${resp}=    Get Request    localhost    /api/v1/getPersonalRelation    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

getPersonalRelationFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    actorId=400  
    ${resp}=    Get Request    localhost    /api/v1/getPersonalRelation    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    404

wonCannesAwardPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    movieId=1000  cannesAwardWon=3
    ${resp}=    Put Request    localhost    /api/v1/wonCannesAward    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

wonCannesAwardFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    movieId=1000  
    ${resp}=    Put Request    localhost    /api/v1/wonCannesAward    data=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400

displayCannesPass
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    movieId=1000  
    ${resp}=    Get Request    localhost    /api/v1/displayCannes    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    200

displayCannesFail
    Create Session    localhost    http://localhost:8080
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${params}=    Create Dictionary    
    ${resp}=    Get Request    localhost    /api/v1/displayCannes    json=${params}    headers=${headers}
    Should Be Equal As Strings    ${resp.status_code}    400