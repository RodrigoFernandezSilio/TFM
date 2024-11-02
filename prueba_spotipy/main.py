import spotipy
from spotipy.oauth2 import SpotifyOAuth

import config

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(
    client_id=config.client_id,
    client_secret=config.client_secret,
    redirect_uri="http://localhost:1234",
    scope="user-library-read"))

taylor_uri = 'spotify:artist:06HL4z0CvFAxyc27GXpf02'

results = sp.artist_albums(taylor_uri, album_type='album')
albums = results['items']
while results['next']:
    results = sp.next(results)
    albums.extend(results['items'])

for album in albums:
    print(album['name'])