import spotipy
from spotipy.oauth2 import SpotifyOAuth

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(
    client_id="485db6344f7845bd9bda3ac767e70aea",
    client_secret="ae22146c55f14ef89aad5c6727ad2e6c",
    redirect_uri="http://localhost:1234",
    scope="user-library-read"))

# Buscar la canción "Viva la Vida" de Coldplay
track_name = "Viva la Vida"
track_info = sp.search(q=track_name, type='track', limit=1)

# Obtener el ID de la canción y luego los detalles de la pista
if track_info['tracks']['items']:
    track_id = track_info['tracks']['items'][0]['id']
    track_details = sp.track(track_id)
    preview_url = track_details['preview_url']

    if preview_url:
        print(f'Preview URL: {preview_url}')
    else:
        print('No hay preview disponible para esta canción.')
else:
    print('No se encontró la canción.')