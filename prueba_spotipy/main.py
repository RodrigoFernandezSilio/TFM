import spotipy
from spotipy.oauth2 import SpotifyOAuth

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(
    client_id="485db6344f7845bd9bda3ac767e70aea",
    client_secret="ae22146c55f14ef89aad5c6727ad2e6c",
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